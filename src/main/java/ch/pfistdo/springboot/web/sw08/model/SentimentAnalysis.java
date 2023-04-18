package ch.pfistdo.springboot.web.sw08.model;

import ai.djl.modality.Classifications;
import ai.djl.repository.zoo.Criteria;
import ai.djl.Application;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import ai.djl.Device;
import ai.djl.inference.Predictor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentimentAnalysis {
    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalysis.class);
    private static Predictor<String, Classifications> predictor;

    public SentimentAnalysis() {}

    public Classifications predict(String input) throws TranslateException {
        // String input = "I like DJL. DJL is the best DL framework!";
        logger.info("input Sentence: {}", input);

        Criteria<String, Classifications> criteria = Criteria.builder()
                .optApplication(Application.NLP.SENTIMENT_ANALYSIS)
                .setTypes(String.class, Classifications.class)
                // This model was traced on CPU and can only run on CPU
                .optDevice(Device.cpu())
                .optProgress(new ProgressBar())
                .build();


            ZooModel<String, Classifications> model;
            try {
                model = criteria.loadModel();
                predictor = model.newPredictor();
            } catch (Exception e) {
                logger.error(input, e);
            }
            
        return predictor.predict(input);
    }

}
