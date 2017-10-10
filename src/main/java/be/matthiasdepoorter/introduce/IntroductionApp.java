package be.matthiasdepoorter.introduce;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.InflaterInputStream;

import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import be.matthiasdepoorter.introduce.domain.Applicant;
import be.matthiasdepoorter.introduce.domain.Degree;
import be.matthiasdepoorter.introduce.domain.Study;
import be.matthiasdepoorter.introduce.domain.Work;

@SpringBootApplication
public class IntroductionApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntroductionApp.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(IntroductionApp.class);
		CVService c = ctx.getBean("Introduce", CVService.class);
		try {
			c.getApplicant();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			ctx.close();
		}
	}
}

@Service("Introduce")
class CVService {

	@Value("http://matthiasdepoorter.herokuapp.com")
	private String baseURL;

	private final RestTemplate template;

	private final File file = new File("CV_MatthiasDePoorter.txt");

	@Autowired
	public CVService(final RestTemplate template) {
		this.template = template;
	}

	public void getApplicant() throws IOException {
		try (TeeOutputStream writer = new TeeOutputStream(Files.newOutputStream(file.toPath()), System.out)) {
			Applicant a = template.getForObject(baseURL + "/Rest/{0}", Applicant.class, "Matthias_De_Poorter");
			writer.write(a.toString().getBytes());
			writer.write("\n\nStudies:\n".getBytes());
			writer.write(this.study(Study::getDegree, a.getStudies()).getBytes());
			writer.write("\n\nWork experience:\n".getBytes());
			writer.write(this.work(a.getWorkExperiences()).getBytes());
			writer.write("\n\nSkills:\n".getBytes());
			writer.write(this.skills(a.getSkills()).getBytes());
		}
	}

	private String study(Function<Study, Degree> programme, Collection<Study> studies) {
		return studies.stream().map(programme).map(Degree::toString).collect(Collectors.joining("\n"));
	}

	private String work(Collection<Work> workExperiences) {
		return workExperiences.stream().filter(Work::isRelevant).sorted().map(Work::toString).collect(Collectors.joining("\n"));
	}

	private String skills(byte[] buf) throws IOException {
		try (ByteArrayInputStream input = new ByteArrayInputStream(buf);
				InflaterInputStream inflate = new InflaterInputStream(input);
				InputStreamReader reader = new InputStreamReader(inflate);
				BufferedReader buffer = new BufferedReader(reader)) {
			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = buffer.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}
			return builder.toString();
		}
	}
}
