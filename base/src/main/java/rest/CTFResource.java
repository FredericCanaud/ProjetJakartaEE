package rest;

import beans.facades.CTFFacade;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("ctf.xhtml")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CTFResource {

    @EJB
    private CTFFacade CTFFacade;

    @GET
    @Path("ctf.xhtml")
    public Response getDerniersCTF() {
        return Response.ok(this.CTFFacade.getDerniersCTF()).build();
    }

    @GET
    @Path("ctf.xhtml")
    public Response getProchainsCTF() {
        return Response.ok(CTFFacade.getProchainsCTF()).build();
    }

    @GET
    @Path("ctf.xhtml?id={id}")
    public Response getCTFFromEquipe(@PathParam("id") String  id) {
        return Response.ok(this.CTFFacade.getCTFFromEquipe(id)).build(); }

    @GET
    @Path("descriptionCTF.xhtml?id={id}")
    public Response getCTF(@PathParam("id") String id) {
        return Response.ok(this.CTFFacade.getCTFFromEquipe(id)).build();
    }

    @GET
    @Path("descriptionCTF.xhtml?id={id}")
    public Response getCommentairesFromCTF(@PathParam("id") String id){
        return Response.ok(this.CTFFacade.getCommentairesFromCTF(id)).build();
    }

    @GET
    @Path("admin/listCTF.xhtml")
    public Response getCTFValides() {
        return Response.ok(this.CTFFacade.getCTFValides()).build();
    }

    @GET
    @Path("admin/listCTF.xhtml")
    public Response getCTFNonValides() {
        return Response.ok(this.CTFFacade.getCTFNonValides()).build();
    }
}
