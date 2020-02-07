package service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileFinderService {

	private Path configFilePath = FileSystems.getDefault()
			.getPath("C:/test");
	
	public void findFile() {
		List<Path> fileWithName = null;
		try {
			fileWithName = Files.walk(this.configFilePath)
					.filter(s -> s.toString().contains("myAmazingFile"))
					.map(Path::getFileName).sorted().collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("FileFinderService --- findFile()");
			e.printStackTrace();
		}
		
		if (fileWithName != null) {
			for (Path name : fileWithName) {
				System.out.println(name.toAbsolutePath());
			}
		} else {
			System.out.println("No match found");
		}
		System.out.println("List fileWithName est vide " + fileWithName);
	}
}
