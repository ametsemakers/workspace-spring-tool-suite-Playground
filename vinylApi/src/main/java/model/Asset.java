package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import utility.ImageUtil;

@Entity
@Table(name = "asset")
public class Asset implements Serializable {

	private static final long serialVersionUID = 8512693490564635065L;
	
	private static Logger log = LoggerFactory.getLogger(Asset.class);
	
	@PrePersist
	public void generateId() {
		
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
	}
	
	@Getter
	@javax.persistence.Id
	private String id;
	
	@Getter
	@Lob
	@Column
	@JsonIgnoreProperties
	private byte[] content;
	
	@Getter
	@Column
	@NotNull
	private String mimeType;
	
	@Getter
	@Column(name = "public")
	@NotNull
	private boolean publicAsset = true;
	
	public static Asset buidImageAsset(byte[] content, String potentialMimeType) {
		Asset image = new Asset();
		image.content = content;
		try {
			image.mimeType = ImageUtil.getMimeType(new ByteArrayInputStream(content));
		} catch (IOException e) {
			log.warn("Failed to get image format");
			image.mimeType = potentialMimeType;
		}
		return image;
	}
	
	public static Asset buidImageAsset(byte[] content) {
		Asset image = new Asset();
		image.content = content;
		try {
			image.mimeType = ImageUtil.getMimeType(new ByteArrayInputStream(content));
		} catch (IOException e) {
			log.warn("Failed to get image format");
			return null;
		}
		return image;
	}
	
	public static Asset buildFileAsset(MultipartFile fileInput) {
		Asset file = new Asset();
		
		return file;
	}
	
	public static Asset buildFileAsset(byte[] content, String mimeType) {
		Asset attachment = new Asset();
		attachment.content = content;
		attachment.mimeType = mimeType;
		
		return attachment;
	}
}
