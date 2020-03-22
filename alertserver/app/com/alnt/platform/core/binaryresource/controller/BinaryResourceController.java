package com.alnt.platform.core.binaryresource.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.core.binaryresource.domain.BinaryResource;
import com.alnt.platform.core.binaryresource.domain.dto.BinaryResourceDTO;
import com.alnt.platform.core.binaryresource.parser.CustomMultipartFormDataBodyParser;
import com.alnt.platform.core.binaryresource.service.BinaryResourceService;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

public class BinaryResourceController extends BaseController<BinaryResource, BinaryResourceDTO> {

	@Inject
	public BinaryResourceController(BinaryResourceService binaryResourceService, HttpExecutionContext ec) {
		super(binaryResourceService, ec, BinaryResource.class, BinaryResourceDTO.class);
	}

	/*
	 * public CompletionStage<Result> upload(Http.Request request) {
	 * Http.MultipartFormData<TemporaryFile> body =
	 * request.body().asMultipartFormData();
	 * Http.MultipartFormData.FilePart<TemporaryFile> fileData =
	 * body.getFile("file"); if (fileData != null) { String fileName =
	 * fileData.getFilename(); long fileSize = fileData.getFileSize(); String
	 * contentType = fileData.getContentType(); TemporaryFile file =
	 * fileData.getRef(); return ok("File uploaded"); } else { return
	 * badRequest().flashing("error", "Missing file"); } }
	 */

	@BodyParser.Of(CustomMultipartFormDataBodyParser.class)
	public CompletionStage<Result> upload(Http.Request request) throws IOException {
		final Http.MultipartFormData<File> formData = request.body().asMultipartFormData();
		final Http.MultipartFormData.FilePart<File> filePart = formData.getFile("file");
		final File file = filePart.getRef();
		final String fileName = filePart.getFilename();
		final long size = Files.size(file.toPath());
		String path = "/tmp/upload/"+fileName;
		File destFile = new File(path);

		BinaryResource data = new BinaryResource();
		/*byte[] bFile = null;
		try {
			bFile = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		FileUtils.copyFile(file, destFile);

		//data.setFileData(bFile);
		data.setTitle(fileName);
		data.setMimeType(filePart.getContentType());
		data.setSize(size);
		data.setPath(path);
		// data.setType(filePart.getDispositionType());

		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return ((BinaryResourceService) getService()).upload(requestDetails, data).thenApplyAsync(savedResource -> {
				return created(Json.toJson(new ApiResponse(Boolean.TRUE, savedResource, null)));
			}, ec.current());
		}, ec.current());
	}
	
	public CompletionStage<Result> download(Http.Request request, Long id) {
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().get(requestDetails, id).thenApplyAsync(optionalResource -> {
				return optionalResource.map(resource -> ok(new File(((BinaryResourceDTO)resource).getPath()))).orElseGet(Results::notFound);
			}, ec.current());
		}, ec.current());
	}
	
	


}
