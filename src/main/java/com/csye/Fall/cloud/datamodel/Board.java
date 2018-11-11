package com.csye.Fall.cloud.datamodel;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Boards")
public class Board {
    private String id;
    private String boardId;
    private String courseId;
    
    public Board() {
    	
    }
    
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() { return id; }
    public void setId(String id) { this.id = id; } 
    
    @DynamoDBIndexHashKey(globalSecondaryIndexName="boardId-index",attributeName="boardId")
   	public String getBoardId() {
   		return boardId;
   	}

   	public void setBoardId(String boardId) {
   		this.boardId = boardId;
   	}

   	@DynamoDBAttribute(attributeName="courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
   	
	@DynamoDBIgnore
	@Override
	public String toString() { 
		return "boardId=" + getBoardId() + ", courseId=" + getCourseId();
	}

	
}