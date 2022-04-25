package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.dtos.request.NewUserDTO;
import com.casalibertad.dtos.request.NewVisitorDTO;
import com.casalibertad.dtos.response.ServiceChannelDTO;
import com.casalibertad.dtos.response.UserDTO;
import com.casalibertad.dtos.response.VisitReasonDTO;
import com.casalibertad.dtos.response.VisitorDTO;
import com.casalibertad.entities.ReasonVisitEntity;
import com.casalibertad.entities.ServiceChannelEntity;
import com.casalibertad.entities.UserEntity;
import com.casalibertad.entities.VisitorEntity;
import com.casalibertad.entities.WorkshopAppointmentReasonEntity;
import com.casalibertad.exceptions.ConflictException;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.repositories.VisitorRepository;

@Service
public class VisitorService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private VisitorRepository visitorRepository;
	@Autowired
	private ReasonVisitService reasonVisitService;
	@Autowired
	private WorkshopAppointmentService workshopAppointmentService;
	@Autowired
	private ServiceChannelService serviceChannelService;


	public VisitorDTO getVisitorDTO(int document_type_id, String document_type_number ) throws NotFoundException {
		VisitorEntity visitorEntity = null;

		visitorEntity = getVisitorEntity(document_type_id, document_type_number);
			
		/*Base case when there is not visitor entity*/
		if(visitorEntity == null) {
			return mapToEmptyVisitorDTO(document_type_id, document_type_number);
		}
			
		return mapToVisitorDTO(visitorEntity);	
	}
	
	public VisitorDTO mapToVisitorDTO(VisitorEntity visitorEntity) {
		
		/* Get entities to map them*/
		ReasonVisitEntity reasonVisitEntity = visitorEntity.getReasonVisit();
		WorkshopAppointmentReasonEntity workshopAppointmentReasonEntity = visitorEntity.getWorkshopAppointment();
		ServiceChannelEntity serviceChannelEntity = visitorEntity.getServiceChannel();
		
		/*Objects where going to map above entities*/
		VisitorDTO visitorDTO = new VisitorDTO();
		VisitReasonDTO reason_visit = new VisitReasonDTO();
		VisitReasonDTO workshop_appointment = new VisitReasonDTO();
		ServiceChannelDTO service_channel = new ServiceChannelDTO();


		reason_visit.setId(reasonVisitEntity.getUniqid()); 
		reason_visit.setReason(reasonVisitEntity.getReason()); 

		/*Workshop Appointment Reason not always is persist*/
		if(workshopAppointmentReasonEntity != null) {
			workshop_appointment.setId(workshopAppointmentReasonEntity.getUniqid());
			workshop_appointment.setReason(workshopAppointmentReasonEntity.getReason());
		}

		service_channel.setId(serviceChannelEntity.getUniqid());
		service_channel.setService_channel(serviceChannelEntity.getChannel());

		visitorDTO.setUniqid(visitorEntity.getUniqid());
		visitorDTO.setUser(userService.mapToUserDTO(visitorEntity.getUser()));
		visitorDTO.setData_processing_consent(visitorEntity.getDataProcessingConsent());
		visitorDTO.setReason_visit(reason_visit);
		visitorDTO.setOther_reason(visitorEntity.getOtherReason());
		visitorDTO.setWorkshop_appointment(workshop_appointment);
		visitorDTO.setService_channel(service_channel);
		
		return visitorDTO;
	}

	/*When there a user, but there is not any visitor entity according*/
	private VisitorDTO mapToEmptyVisitorDTO(int document_type_id, String document_type_number) throws NotFoundException {
		VisitorDTO visitorDTO = new VisitorDTO();
		UserDTO userDTO = userService.getUserDTO(document_type_id, document_type_number);
		if(userDTO != null) {
			visitorDTO.setUser(userDTO);
		}
		return visitorDTO;
	}
	
	public VisitorEntity getVisitorEntity(int document_type_id, String document_type_number) {
		VisitorEntity visitorEntity = null;
		try {
			UserEntity userEntity = userService.getUserEntity(document_type_id, document_type_number);
			visitorEntity = visitorRepository.findFirstByUserOrderByCreatedDateDesc(userEntity); 
			if(visitorEntity == null) {
				return null;
			}
			visitorEntity.setUser(userEntity);
		}catch(NotFoundException e) {
			/*Catching error when there's not a user with document type and number provided*/
		}
		
		return visitorEntity;
	}

	public boolean isValidVisitorDTO(NewVisitorDTO newVisitorDTO) throws NotFoundException {
		boolean valid = false;
		
		if(reasonVisitService.isValidReasonVisitEntity(newVisitorDTO.getReason_visit_id())
				&& serviceChannelService.isValidServiceChannelEntity(newVisitorDTO.getService_channel_id())) {
			
			if(newVisitorDTO.getWorkshop_appointment_id() != 0) {
				workshopAppointmentService.isValidWorkshopAppointment(newVisitorDTO.getWorkshop_appointment_id());
			}
			valid = true;
		}
		
		
		return valid;
	}

	public VisitorEntity createVisitorEntity(NewVisitorDTO newVisitorDTO) throws NotFoundException {
		
		int workshopAppointmentId = newVisitorDTO.getWorkshop_appointment_id();
		VisitorEntity visitorEntity = new VisitorEntity();
		
		NewUserDTO userDTO = newVisitorDTO.getUser();
		
		/*Creating entity fields*/
		UserEntity userEntity = userService.getUserEntity(userDTO.getDocument_type_id(), userDTO.getDocument_number());
		ReasonVisitEntity reasonVisitEntity = reasonVisitService.getReasonVisitEntity(newVisitorDTO.getReason_visit_id());
		
		/*Workshop appointment not always will be given*/
		WorkshopAppointmentReasonEntity workshopAppointmentReasonEntity = null;
		if(workshopAppointmentId != 0) {
			workshopAppointmentReasonEntity = workshopAppointmentService.getWorkshopAppointment(newVisitorDTO.getWorkshop_appointment_id());
		}

		ServiceChannelEntity serviceChannelEntity = serviceChannelService.getServiceChannelEntity(
				newVisitorDTO.getService_channel_id());
		
		/*Filling the new visitor entity*/
		visitorEntity.setUser(userEntity);
		visitorEntity.setDataProcessingConsent(newVisitorDTO.getData_processing_consent());
		visitorEntity.setReasonVisit(reasonVisitEntity);
		visitorEntity.setOtherReason(newVisitorDTO.getOther_reason());
		visitorEntity.setWorkshopAppointment(workshopAppointmentReasonEntity);
		visitorEntity.setServiceChannel(serviceChannelEntity);
		
		return visitorRepository.save(visitorEntity);
	}
	
}
