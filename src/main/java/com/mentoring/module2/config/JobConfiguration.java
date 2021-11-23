package com.mentoring.module2.config;

import com.mentoring.module2.dto.TicketDto;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JobConfiguration {

    @Value("${tickets.path}")
    private String ticketsFilePath;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Writer writer;

    @Bean
    public StaxEventItemReader<TicketDto> ticketItemReader() {
        Map<String, Class> aliases = new HashMap<>();
        aliases.put("ticket", TicketDto.class);

        TicketConverter converter = new TicketConverter();
        XStreamMarshaller ummarshaller = new XStreamMarshaller();
        ummarshaller.setAliases(aliases);
        ummarshaller.setConverters(converter);
        ummarshaller.setTypePermissions(AnyTypePermission.ANY);

        StaxEventItemReader<TicketDto> reader = new StaxEventItemReader<>();
        reader.setResource(new ClassPathResource(ticketsFilePath));
        reader.setFragmentRootElementName("ticket");
        reader.setUnmarshaller(ummarshaller);

        return reader;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<TicketDto, TicketDto>chunk(2000)
                .reader(ticketItemReader())
                .writer(writer)
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
}
