package com.exam.secure.publicAllowanceController.quizController;

import com.exam.secure.entities.categoryEntities.ResultModel;
import com.exam.secure.entities.fileResourceEntities.FileResource;
import com.exam.secure.publicAllowanceController.modelTemp.QuestionAnswerTempModel;
import com.exam.secure.publicAllowanceController.urlMappings.UrlMappingPublic;
import com.exam.secure.quizinterface.SubmitQuizInterface;
import com.exam.secure.repository.fileResourceRepository.FileResourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(UrlMappingPublic.API_PUBLIC_AUTH_CRED)
public class SubmitQuizController {

    @Autowired
    private SubmitQuizInterface submitQuizInterface;

    @Autowired
   private ResourceLoader resourceLoader;

    @Autowired
    private FileResourceRepository fileResourceRepository;


    @PostMapping(UrlMappingPublic.SUBMIT_QUIZ)
    public ResponseEntity<?> submitQuiz(@PathVariable String timeDuration,@RequestBody List<QuestionAnswerTempModel> questionAnswerTempModels)
    {
       ResultModel resultModel  =  this.submitQuizInterface.submitQuizResult(questionAnswerTempModels,timeDuration);
       if(resultModel != null)
       {
            return ResponseEntity.ok(resultModel);
       }
       else
       {
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
       }
    }

    @PostMapping(UrlMappingPublic.SEND_REPORT_TO_EMAIL)
    public ResponseEntity<?> sendReportToEmail(@PathVariable String timeDuration,@PathVariable String email,@RequestBody List<QuestionAnswerTempModel> questionAnswerTempModels)
    {
        ResultModel result  =  this.submitQuizInterface.sendReportToEmail(questionAnswerTempModels , timeDuration, email);
        if(result != null)
        {
            return ResponseEntity.ok(result);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    private String htmlData;
    @PostMapping(UrlMappingPublic.GENERATE_QUIZ_RESULT_PDF)
    public String generateQuizResultPdf(HttpServletResponse response,@PathVariable String timeDuration,@RequestBody List<QuestionAnswerTempModel> questionAnswerTempModels) throws IOException {
        ResultModel resultModel  =  this.submitQuizInterface.submitQuizResult(questionAnswerTempModels,timeDuration);

//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("quiz-result-pdf/quizResultPdf.html").getFile());


        //READ fILE
       //FileInputStream fl = new FileInputStream(file);
//        byte[] arr = new byte[(int)getReportTemplate().length()];
//        fl.read(arr);
//        fl.close();

        StringBuilder builder=new StringBuilder();

//        Resource resource4 = resourceLoader.getResource("/index.html");
//        byte[] copyToByteArray4 = FileCopyUtils.copyToByteArray(resource4.getInputStream());
        String index = new String(getReportTemplate().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        builder.append(index);
//        ResultModel resultModel1 = new ResultModel();
//       // resultModel.setCorrectAnswer(100);
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, String> map = oMapper.convertValue(resultModel,Map.class);

        for (String key : map.keySet()) {
            String value = String.valueOf(map.get(key));
            value = value == null ? "null" : value;
            index = index.replace("{{"+ key +"}}", value);
        }

        System.out.println("******PERPARE TO FLY******");
        ByteArrayOutputStream target = new ByteArrayOutputStream();

        //HTML TO PDF CONV...
        HtmlConverter.convertToPdf(index, target);
        ServletOutputStream outputStream = response.getOutputStream();


        response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
        response.setHeader("Content-Disposition","attachment; filename=\"" + "quizResultPdf.pdf" + "\"");
        outputStream.write(target.toByteArray());
        return index;
    }



    public  String getReportTemplate()
    {
         FileResource resource = fileResourceRepository.findAll().get(0);
          if(resource.getUrl() == null || resource.getUrl().isEmpty())
          {
              resource.setUrl("https://img.freepik.com/free-vector/studying-concept-illustration_114360-1301.jpg?w=2000");
          }
//        return "<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<head>\n" +
//                "    <style>\n" +
//                "        @page {\n" +
//                "            size: A4;\n" +
//                "            margin: 0;\n" +
//                "            }\n" +
//                "\n" +
//                "    @media screen{\n" +
//                "    .page-number:before {\n" +
//                "        counter-increment: page;\n" +
//                "        content: \"Page \" counter(page);\n" +
//                "        }\n" +
//                "     }\n" +
//                "\n" +
//                "table {\n" +
//                "  border-collapse: collapse;\n" +
//                "  width: 100%;\n" +
//                "}\n" +
//                "\n" +
//                "th, td {\n" +
//                "  text-align: left;\n" +
//                "  padding: 8px;\n" +
//                "}\n" +
//                "\n" +
//                ".h2Heading\n" +
//                "{\n" +
//                "  text-align: center;\n" +
//                "}\n" +
//                "\n" +
//                ".para001\n" +
//                "{\n" +
//                "  text-align: center;\n" +
//                "  margin-left: 20%;\n" +
//                "  margin-right: 20%;\n" +
//                "}\n" +
//                ".divFirst\n" +
//                "{\n" +
////                "  background-color: #EBF5FB;\n" +
//                "  border-radius: 10px;\n" +
//                "  padding: 20px;\n" +
//                "}\n" +
//                "\n" +
//                "tr:nth-child(even) {background-color: #f2f2f2;}\n" +
//                "</style>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "\n" +
////                "<div class=\"divFirst\">\n" +
////                "    <h2 class=\"h2Heading\">Responsive Table</h2>\n" +
////                "    <p class=\"para001\">A responsive table will display a horizontal scroll bar if the screen is too\n" +
////                "        small to display the full content. Resize the browser window to see the effect:</p>\n" +
////                "\n" +
//                "    <div style=\"position: relative;text-align: center;\">\n" +
//                "        <img " +
//                "src="+resource.getUrl()+" " +
//                "height=\"300\" width=\"400\"  >\n" +
//                "    </div>\n" +
//                "\n" +
//                "\n" +
//                "</div>\n" +
//                "\n" +
//                "<div style=\"overflow-x: auto;padding: 20px;\">\n" +
//                "    <table>\n" +
//                "        <tr style=\"background-color: #D1E2F9;\">\n" +
//                "            <th>SNO</th>\n" +
//                "            <th>ATTEMPT-QUESTION</th>\n" +
//                "            <th>CORRECT-ANSWER</th>\n" +
//                "            <th>PERCENTAGE</th>\n" +
//                "            <th>TOTAL QUESTIONS</th>\n" +
//                "            <th>WRONG ANSWERS </th>\n" +
//                "            <th>TIME DURATION</th>\n" +
//                "        </tr>\n" +
//                "        <tr>\n" +
//                "            <td>1</td>\n" +
//                "            <td>{{attemptQuestions}}</td>\n" +
//                "            <td>{{correctAnswer}}</td>\n" +
//                "            <td>{{percentage}}</td>\n" +
//                "            <td>{{totalQuestion}}</td>\n" +
//                "            <td>{{wrongAnswer}}</td>\n" +
//                "            <td>{{timeDuration}}</td>\n" +
//                "        </tr>\n" +
//                "    </table>\n" +
//                "</div>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>\n" +
//                "\n";

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        @page {\n" +
                "            size: A4;\n" +
                "            margin: 0;\n" +
                "            }\n" +
                "\n" +
                "    @media screen{\n" +
                "    .page-number:before {\n" +
                "        counter-increment: page;\n" +
                "        content: \"Page \" counter(page);\n" +
                "        }\n" +
                "     }\n" +
                "\n" +
                "table {\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "th, td {\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                ".h2Heading\n" +
                "{\n" +
                "  text-align: center;\n" +
                "}\n" +
                "\n" +
                ".para001\n" +
                "{\n" +
                "  text-align: center;\n" +
                "  margin-left: 20%;\n" +
                "  margin-right: 20%;\n" +
                "}\n" +
                ".divFirst\n" +
                "{\n" +
                "  background-color: #EBF5FB;\n" +
                "  border-radius: 10px;\n" +
                "  padding: 20px;\n" +
                "}\n" +
                "\n" +
                ".container {\n" +
                "  padding: 2px 16px;\n" +
                "}\n" +
                "\n" +
                ".left {\n" +
                "  background-color: #E5E7E9;\n" +
                "  padding: 20px;\n" +
                "  float: left;\n" +
                "  width: 20%; /* The width is 20%, by default */\n" +
                "   margin-left: 100px;\n" +
                "   border-radius: 10px;\n" +
                "}\n" +
                "\n" +
                ".main {\n" +
                "  background-color: white;\n" +
                "  padding: 20px;\n" +
                "  float: left;\n" +
                "  width: 10px; /* The width is 60%, by default */\n" +
                "}\n" +
                "\n" +
                ".right {\n" +
                "  background-color: #E5E7E9;\n" +
                "  padding: 20px;\n" +
                "  float: left;\n" +
                "  width: 20%; /* The width is 20%, by default */\n" +
                "  border-radius: 10px;\n" +
                "\n" +
                " \n" +
                "}\n" +
                "\n" +
                ".cl100\n" +
                "{\n" +
                "    text-align: center;\n" +
                "    font-family: inherit;\n" +
                "    font-weight: 600;\n" +
                "    color: #2E4053;\n" +
                "}\n" +
                "\n" +
                ".cl200\n" +
                "{\n" +
                "    color: #5D6D7E;\n" +
                "    text-align: center;\n" +
                "}\n" +
                "\n" +
                ".cl300\n" +
                "{\n" +
                "    margin-top: 0px;\n" +
                "}\n" +
                "\n" +
                ".cl400\n" +
                "{\n" +
                "    margin-top: 160px;\n" +
                "}\n" +
                "\n" +
                ".cl500\n" +
                "{\n" +
                "    margin-top: 320px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {background-color: #f2f2f2;}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<!-- <div class=\"divFirst\">\n" +
                "    <h2 class=\"h2Heading\">Responsive Table</h2>\n" +
                "    <p class=\"para001\">A responsive table will display a horizontal scroll bar if the screen is too\n" +
                "        small to display the full content. Resize the browser window to see the effect:</p> -->\n" +
                "\n" +
                "    <div style=\"position: relative;text-align: center;\">\n" +
                " <img " +
                "src="+resource.getUrl()+" " +
                " height=\"300\" width=\"400\"  >\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "<!-- <div style=\"overflow-x: auto;padding: 20px;\">\n" +
                "    <table>\n" +
                "        <tr style=\"background-color: #D1E2F9;\">\n" +
                "            <th>SNO</th>\n" +
                "            <th>ATTEMPT-QUESTION</th>\n" +
                "            <th>CORRECT-ANSWER</th>\n" +
                "            <th>PERCENTAGE</th>\n" +
                "            <th>TOTAL QUESTIONS</th>\n" +
                "            <th>WRONG ANSWERS </th>\n" +
                "            <th>TIME DURATION</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>1</td>\n" +
                "            <td>{{attemptQuestions}}</td>\n" +
                "            <td>{{correctAnswer}}</td>\n" +
                "            <td>{{percentage}}</td>\n" +
                "            <td>{{totalQuestion}}</td>\n" +
                "            <td>{{wrongAnswer}}</td>\n" +
                "            <td>{{timeDuration}}</td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</div>\n" +
                " -->\n" +
                "\n" +
                "\n" +
                "<div  class=\"cl300\">\n" +
                "     <div class=\"left\">\n" +
                "      <p class=\"cl100\">Attempt Question</p>\n" +
                "       <p class=\"cl200\">{{attemptQuestions}}</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"main\">\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"left\">\n" +
                "      <p class=\"cl100\">CORRECT-ANSWER</p>\n" +
                "       <p class=\"cl200\">{{correctAnswer}}</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"cl400\">\n" +
                "    <div class=\"left\">\n" +
                "  <p class=\"cl100\">PERCENTAGE</p>\n" +
                "   <p class=\"cl200\">{{percentage}}</p>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"main\">\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"left\">\n" +
                "  <p class=\"cl100\">CORRECT-ANSWER</p>\n" +
                "   <p class=\"cl200\">{{correctAnswer}}</p>\n" +
                " </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"cl500\">\n" +
                "    <div class=\"left\">\n" +
                "  <p class=\"cl100\">WRONG ANSWERS</p>\n" +
                "   <p class=\"cl200\">{{wrongAnswer}}</p>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"main\">\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"left\">\n" +
                "  <p class=\"cl100\">  TIME DURATION</p>\n" +
                "   <p class=\"cl200\">{{timeDuration}}</p>\n" +
                " </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
    }



}
