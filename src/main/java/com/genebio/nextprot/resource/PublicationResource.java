package com.genebio.nextprot.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genebio.nextprot.domain.Publication;
import com.genebio.nextprot.service.PublicationService;

@Service
@Path("publication")
public class PublicationResource {

	@Autowired
	private PublicationService publicationService;
	
	// http://localhost:8080/jersey-api/publication/
	@GET
	@Produces(value = {"application/xml", "application/json"})
	public Response hello() {
		return Response.status(200).entity(new Publication()).build();
	}
	
	// http://localhost:8080/jersey-api/publication/6634104/
	@GET
	@Path("{pid}")
	@Produces(value = {"application/xml", "application/json"})
	public Response get(@PathParam("pid") String pid) {
		
		return Response.status(200).entity(this.publicationService.getPublicationById(Integer.parseInt(pid))).build();
	}
	
	@GET
	@Path("/list/{title}")
	@Produces(value = {"application/json"})
	public Response getByTitle(@PathParam("title") String title) {
		return Response.status(200).entity(this.publicationService.getPublicationByTitle("%"+title+"%")).build();
	}
}
