CREATE TABLE jpaFiles
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    fileName VARCHAR(255),
    mime VARCHAR(255),
    fileSize BIGINT,
    uploadDate DATE
);