package com.csye.Fall.cloud.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye.Fall.cloud.datamodel.Announcement;
import com.csye.Fall.cloud.datamodel.Board;
import com.csye.Fall.cloud.datamodel.DynamoDbConnector;

public class AnnouncementService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public AnnouncementService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	// Getting a list of all announcements
	// GET "..webapi/announcements"
	public List<Announcement> getAllAnnouncements() {
		// Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("boardId-announcementId-index")
				.withConsistentRead(false);

		List<Announcement> list = mapper.scan(Announcement.class, scanExpression);
		return list;
	}

	// Adding a announcement
	public Announcement addAnnouncement(Announcement announcement) {
		Announcement ann = new Announcement();
		ann.setBoardId(announcement.getBoardId());
		ann.setAnnouncementId(announcement.getAnnouncementId());
		ann.setAnnouncementText(announcement.getAnnouncementText());
		mapper.save(ann);

		return ann;
	}

	// Getting One announcement
	public Announcement getAnnouncement(String boardId, String announcementId) {
		List<Announcement> list = getAnnouncementFromDDB(boardId,announcementId);
		return list.size() != 0 ? list.get(0) : null;
	}

	// Deleting an Announcement
	public Announcement deleteAnnouncement(String boardId, String announcementId) {
		List<Announcement> list = getAnnouncementFromDDB(boardId, announcementId);
		Announcement ann = null;
		if (list.size() != 0) {
			ann = list.get(0);
			mapper.delete(ann);
			Announcement deletedAnnouncement = mapper.load(Announcement.class, ann.getId());

			if (deletedAnnouncement == null) {
				System.out.println("Done - The announcement is deleted.");
			}
		}

		return ann;
	}

	// Updating announcement Info
	public Announcement updateAnnouncementInformation(String boardId, String announcementId, Announcement announcement) {
		List<Announcement> list = getAnnouncementFromDDB(boardId, announcementId);
		Announcement ann = null;
		if (list.size() != 0) {
			ann = list.get(0);
			ann.setAnnouncementText(announcement.getAnnouncementText());;
			mapper.save(ann);

			System.out.println("Item updated.");
			
		}

		return ann;
	}

	// helper function
	public List<Announcement> getAnnouncementFromDDB(String boardId, String announcementId ) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		eav.put(":v2", new AttributeValue().withS(announcementId));

		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1 and announcementId = :v2")
				.withExpressionAttributeValues(eav);

		List<Announcement> list = mapper.query(Announcement.class, queryExpression);
		return list;
	}
	
	public List<Announcement> getAnnouncementsByBoardId(String boardId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);

		List<Announcement> list = mapper.query(Announcement.class, queryExpression);
		return list;
	}
	
	public void deleteAnnouncementsByBoardId(String boardId){
		List<Announcement> list = getAnnouncementsByBoardId(boardId);
		if(list.size()!= 0){
			mapper.batchDelete(list);
		}
	}

}
