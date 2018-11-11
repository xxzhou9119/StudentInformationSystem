package com.csye.Fall.cloud.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye.Fall.cloud.datamodel.Board;
import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.datamodel.DynamoDbConnector;


public class BoardService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public BoardService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Getting a list of all boards
	// GET "..webapi/boards"
	public List<Board> getAllBoards() {	
		//Getting the list
	    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			  .withIndexName("boardId-index")
			  .withConsistentRead(false);

		List<Board> list =  mapper.scan(Board.class, scanExpression);
	    return list ;
	}
		
	// Adding a professor
	public Board addBoard(Board board) {
		 Board board2 = new Board();
		 board2.setBoardId(board.getBoardId());
		 board2.setCourseId(board.getCourseId());
		 mapper.save(board2);
				
		 System.out.println("Item added:");
	     System.out.println(board2.toString());
			    
		 return board2;
	}
		
	// Getting One Board
	public Board getBoard(String boardId) {
		List<Board> list = getBoardFromDDB(boardId);
		return list.size() != 0 ? list.get(0) : null;
	}
			
	// Deleting a board
	public Board deleteBoard(String boardId) {
	   List<Board> list = getBoardFromDDB(boardId);
	   Board board = null;
	   if(list.size() != 0){
		   board = list.get(0);
		   String deleteBoardId = board.getBoardId();
		   mapper.delete(board);
		   
		   // deleting corresponding announcements in Announcements table 
		   AnnouncementService annService = new AnnouncementService();
		   annService.deleteAnnouncementsByBoardId(deleteBoardId);
		   
		   Board deletedBoard = mapper.load(Board.class, board.getId());		
		   if (deletedBoard == null) {
			    System.out.println("Done - The board is deleted.");
			    System.out.println(board.toString());
		   }
	   }
				
		 return board;
    }
			
	// Updating board Info
	public Board updateBoardInformation(String boardId, Board board) {	
		List<Board> list = getBoardFromDDB(boardId);
		Board oldBoard = null;
		if(list.size() != 0) {
			oldBoard = list.get(0);
			oldBoard.setCourseId(board.getCourseId());
			mapper.save(oldBoard);
			
			System.out.println("Item updated:");
			System.out.println(oldBoard.toString());
		}

		return oldBoard;
	}
		
			
   // helper function 
	public List<Board> getBoardFromDDB(String boardId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
			.withIndexName("boardId-index")
			.withConsistentRead(false)
			.withKeyConditionExpression("boardId = :v1")
			.withExpressionAttributeValues(eav);

		List<Board> list =  mapper.query(Board.class, queryExpression);
		return list;
   }
			
		
}
