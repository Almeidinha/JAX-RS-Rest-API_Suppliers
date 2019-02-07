package com.almeida.supplier.resources;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Test;

import com.almeida.supplier.entity.Supplier;
import com.almeida.supplier.resources.SupplierResources;

class SupplierResourcesTest extends JerseyTest {
	
    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(SupplierResources.class);
    }

    
	@Test
	public void testFindAll() {
		Response response = target("/resources/suppliers").request().get();
		assertEquals(200, response.getStatus(), "should return status 200");
		assertNotNull(response.getEntity(), "Should return list");
	}

	@Test
	public void testFindById() {
        Response response = target("/supplier/resources/suppliers/1").request().get();
        assertEquals(200, response.getStatus(), "Should return status 200");
        assertNotNull(response.getEntity(), "Should return notification");
	}
	
    @Test
    public void testFetchByFail_DoesNotHaveDigit(){
        Response response = target("/supplier/resources/suppliers/no-id-digit").request().get();
        assertEquals(404, response.getStatus(), "Should return status 404");
    }

	@Test
	public void testSave() { 
        Supplier supplier = this.getSupplier();
        Response output = target("/supplier/resources/suppliers")
                .request()
                .post(Entity.entity(supplier, MediaType.APPLICATION_JSON));

        assertEquals(200, output.getStatus(), "Should return status 200");
        assertNotNull(output.getEntity(), "Should return supplier");
	}

	@Test
	public void testUpdate() {
		Supplier supplier = this.getSupplier();   
		
	    Response output = target("/supplier/resources/suppliers")
	    		.request()
	            .put(Entity.entity(supplier, MediaType.APPLICATION_JSON));
	   assertEquals(204, output.getStatus(), "Should return status 204");
	}

	@Test
	public void testDelete() {
        Response output = target("/supplier/resources/suppliers/1").request().delete();
        assertEquals(204, output.getStatus(), "Should return status 204");
	}
	
	private Supplier getSupplier() {
		Supplier supplier = new Supplier();
		supplier.setId((long) 999);
		supplier.setName("Fornecedor da Silva");
		supplier.setEmail("dasilva@teste.com");
		supplier.setCNPJ("123456789");
		supplier.setComment("This is some lore epsun comment!");
		
		return supplier;
	}

}
