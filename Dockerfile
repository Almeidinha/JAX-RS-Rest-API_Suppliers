FROM airhacks/glassfish
COPY ./target/supplier.war ${DEPLOYMENT_DIR}
