package com.csye.Fall.cloud.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye.Fall.cloud.datamodel.Board;
import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.services.BoardService;

//.. /webapi/myresource
@Path("/boards")
public class BoardResource {
	BoardService boardSer = new BoardService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getAllBoards() {

		return boardSer.getAllBoards();

	}

	// ... webapi/boards/..
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoard(@PathParam("boardId") String boardId) {
		return boardSer.getBoard(boardId);
	}

	@DELETE
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteBoard(@PathParam("boardId") String boardId) {
		return boardSer.deleteBoard(boardId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board board){
		return boardSer.addBoard(board);
	}

	@PUT
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("boardId") String boardId, 
			Board board) {
		return boardSer.updateBoardInformation(boardId, board);
	}

}
