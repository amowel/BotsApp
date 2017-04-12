package com.weblab.service.basic;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;

;

@Service
@Slf4j
public class PollyService {

    private final AmazonPollyClient polly;
    private final Voice voice;

    public PollyService(Region region) {

        polly = new AmazonPollyClient(new ClasspathPropertiesFileCredentialsProvider("AwsPollyCredentials.properties"),
                new ClientConfiguration());
        polly.setRegion(region);
        voice = new Voice();
        voice.setId("Maxim");
    }

    public InputStream synthesize(String text, OutputFormat format) throws IOException {
        SynthesizeSpeechRequest synthReq =
                new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
                        .withOutputFormat(format);
        SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

        return synthRes.getAudioStream();
    }

    public InputStream synthesize(String text, OutputFormat format, String voice) throws IOException {
        SynthesizeSpeechRequest synthReq =
                new SynthesizeSpeechRequest().withText(text).withVoiceId(voice)
                        .withOutputFormat(format);
        SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

        return synthRes.getAudioStream();
    }


    public File createFile(String text, String author) throws IOException {
        String rootPath = System.getProperty("user.home");
        String imagePath = rootPath + File.separator + "/tmp";
        String identity = LocalDateTime.now().toString();
        File file = new File(imagePath
                + author + "|" + identity + ".3g");
        OutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(this.synthesize(text, OutputFormat.Mp3), outputStream);
        outputStream.close();
        return file;
    }


}