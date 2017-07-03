package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.EventMaster;

public interface EventMasterDAO {

	public List<EventMaster> getAllEvents(char status);

	public EventMaster getEventByID(int evtid);
	
	public boolean saveEvent(EventMaster event);
	
	public boolean removeEvent(int evtid);
	
}
