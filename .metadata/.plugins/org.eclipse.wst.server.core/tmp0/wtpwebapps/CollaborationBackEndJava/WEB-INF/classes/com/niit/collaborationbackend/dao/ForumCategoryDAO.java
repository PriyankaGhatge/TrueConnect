package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.ForumCategory;

public interface ForumCategoryDAO {
	
	public List<ForumCategory> getAllForumCategory();
	
	public boolean forumCategoryUpdate(ForumCategory forumcategory);
		
	public ForumCategory getForumCategoryByID(int fcid);

}
