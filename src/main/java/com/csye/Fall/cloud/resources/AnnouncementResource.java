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

import com.csye.Fall.cloud.datamodel.Announcement;
import com.csye.Fall.cloud.services.AnnouncementService;


//.. /webapi/myresource
@Path("/announcements")
public class AnnouncementResource {
	AnnouncementService annService = new AnnouncementService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAllAnnouncements() {
		return annService.getAllAnnouncements();
	}

	// ... webapi/announcements/..-..
	@GET
	@Path("/{boardId}-{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement getAnnouncement(@PathParam("boardId") String boardId, @PathParam("announcementId")String announcementId) {
		return annService.getAnnouncement(boardId, announcementId);
	}
	
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAnnouncementsByBoardId(@PathParam("boardId") String boardId) {
		return annService.getAnnouncementsByBoardId(boardId);
	}

	@DELETE
	@Path("/{boardId}-{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement deleteAnnouncement(@PathParam("boardId") String boardId,@PathParam("announcementId")String announcementId) {
		return annService.deleteAnnouncement(boardId, announcementId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement addAnnouncement(Announcement announcement){
		return annService.addAnnouncement(announcement);
	}

	@PUT
	@Path("/{boardId}-{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement updateAnnouncement(@PathParam("boardId") String boardId, @PathParam("announcementId")String announcementId,
			Announcement announcement) {
		return annService.updateAnnouncementInformation(boardId, announcementId, announcement);
	}
}
