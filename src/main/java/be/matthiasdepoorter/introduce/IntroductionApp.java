package be.matthiasdepoorter.introduce;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.function.Function;
import java.util.zip.InflaterInputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import be.matthiasdepoorter.introduce.domain.Applicant;
import be.matthiasdepoorter.introduce.domain.Degree;
import be.matthiasdepoorter.introduce.domain.Study;
import be.matthiasdepoorter.introduce.domain.Work;

@SpringBootApplication
public class IntroductionApp {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(IntroductionApp.class);
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(IntroductionApp.class);
		CVService c = ctx.getBean("Introduce", CVService.class);
		try {
			c.getApplicant();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally{
			ctx.close();
		}
	}
}

@Service("Introduce")
class CVService {

	@Value("http://matthiasdepoorter.herokuapp.com")
	private String baseURL;

	@Autowired
	private RestTemplate template;

	public void getApplicant() throws IOException {
		Applicant a = template.getForObject(baseURL + "/Rest/{0}", Applicant.class, "Matthias_De_Poorter");
		System.out.println("\n"+a);
		System.out.println("\nStudies:");
		this.study(s -> s.getDegree(), a.getStudies());
		System.out.println("\nWork experience:");
		this.work(a.getWorkExperiences());
		System.out.println("\nSkills:");
		this.skills(a.getSkills());
	}

	private void study(Function<Study, Degree> programme, Collection<Study> studies) {
		for (Study s : studies) {
			System.out.println(programme.apply(s));
		}
	}

	private void work(Collection<Work> workExperiences) {
		workExperiences.stream().filter(w -> w.isRelevant()).sorted().forEach(System.out::println);
	}

	private void skills(byte[] buf) throws IOException {
		try (ByteArrayInputStream input = new ByteArrayInputStream(buf);
				InflaterInputStream inflate = new InflaterInputStream(input);
				InputStreamReader reader = new InputStreamReader(inflate);
				BufferedReader buffer = new BufferedReader(reader);) {
			String line;
			while ((line = buffer.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
