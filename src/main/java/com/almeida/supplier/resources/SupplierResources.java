package com.almeida.supplier.resources;

import com.almeida.supplier.ResourceUriBuilder;
import com.almeida.supplier.entity.Supplier;
import com.almeida.supplier.repository.SupplierRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Stateless
@Path("suppliers") // messages
public class SupplierResources {

    @Inject
    SupplierRepository message;

    @Inject
    ResourceUriBuilder resourceUriBuilder;

    @Context
    UriInfo uriInfo;
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray findAll() {
        
    	JsonArrayBuilder list = Json.createArrayBuilder();
        List<Supplier> all = this.message.findAll();
        all.stream()
                .map(m -> m.toJson(
                		resourceUriBuilder.createResourceUri(
                				SupplierResources.class,
                                "findById",
                                m.getId(),
                                uriInfo
                                )
                        )
                )
                .forEach(list::add);
        return list.build();
    }
 
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
    	Supplier supplier = this.message.findById(id);
        
    	if (supplier != null) {
    		final URI self = resourceUriBuilder.createResourceUri(
                    SupplierResources.class, "findById", supplier.getId(), uriInfo
            );
            return Response.status(200).entity(supplier.toJson(self)).build();
    	} else {
    		return Response.status(404).entity(Json.createObjectBuilder()
    				.add("message", "ID '" + id + "' não encontrado.").build()).build();
    	}
    	
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid Supplier supplier) {
        this.message.create(supplier);
        final URI self = resourceUriBuilder.createResourceUri(
                SupplierResources.class, "findById", supplier.getId(), uriInfo
        );
        return Response.status(201).entity(supplier.toJson(self)).build();
    }
    
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @Valid Supplier newSupplier) {
    	Supplier supplier = this.message.findById(id);
    	
    	if (supplier != null) {
    		supplier.setName(newSupplier.getName());
        	supplier.setEmail(newSupplier.getEmail());
        	supplier.setCNPJ(newSupplier.getCNPJ());
        	supplier.setComment(newSupplier.getComment());
            this.message.update(supplier);
        	
            final URI self = resourceUriBuilder.createResourceUri(
                    SupplierResources.class, "findById", supplier.getId(), uriInfo
            );
            return Response.status(200).entity(supplier.toJson(self)).build();
    	} else {
    		return Response.status(404).entity(Json.createObjectBuilder()
    				.add("message", "ID '" + id + "' não encontrado").build()).build();
    	}
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
    	Supplier supplier = this.message.findById(id);
    	
    	if (supplier != null) {
    		this.message.remove(supplier);
    		
    		return Response.status(200).entity(Json.createObjectBuilder()
    				.add("message", "O fornecedor com ID '" + id + "' foi removido").build()).build();
    	} else {
    		return Response.status(404).entity(Json.createObjectBuilder()
    				.add("message", "ID " + id + " não encontrado").build()).build();
    	}
    }
       
}
