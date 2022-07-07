package com.exam.secure.publicAllowanceService.quizSumbitService;

import com.exam.secure.emailConfig.EmailSender;
import com.exam.secure.entities.categoryEntities.ResultModel;
import com.exam.secure.entities.fileResourceEntities.FileResource;
import com.exam.secure.publicAllowanceController.modelTemp.QuestionAnswerTempModel;
import com.exam.secure.quizinterface.SubmitQuizInterface;
import com.exam.secure.repository.categoryRepository.QuestionAnswerRepository;
import com.exam.secure.repository.fileResourceRepository.FileResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizSubmitServicePublic implements SubmitQuizInterface {

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private FileResourceRepository fileResourceRepository;




    @Override
    public ResultModel submitQuizResult(List<QuestionAnswerTempModel> questionAnswerTempModels,String timeDuration) {

        int correctAnswer = 0;
        int totalQuestion = 0;
        int wrongAnswer = 0;
        int attemptQuestion =0;

        //TOTAL QUESTIONS SIZE
        totalQuestion  = wrongAnswer = questionAnswerTempModels.size();

        try {
                for(QuestionAnswerTempModel qatm : questionAnswerTempModels )
                {
                    if(this.questionAnswerRepository.findById(qatm.getId()).get().getAnsKey().trim().equals(qatm.getGivenAnswer()))
                    {
                        correctAnswer++;
                        wrongAnswer--;
                    }
                    //COUNT ATTEMPT QUESTIONS
                    if(qatm.getGivenAnswer() != null)
                    {
                      attemptQuestion++;
                    }

                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Get Percentage
        float percentage = (correctAnswer / (float)totalQuestion * 100);

        ResultModel resultModel =new ResultModel();
        resultModel.setCorrectAnswer(correctAnswer);
        resultModel.setWrongAnswer(wrongAnswer);
        resultModel.setTotalQuestion(attemptQuestion);
        resultModel.setAttemptQuestions(attemptQuestion);
        resultModel.setPercentage(percentage);
        resultModel.setTimeDuration(timeDuration);

        return resultModel;
    }

    @Override
    public ResultModel sendReportToEmail(List<QuestionAnswerTempModel> questionAnswerTempModels, String timeDuration,String email) {

        if(email== null || email =="")
        {
            return null;
        }

        int correctAnswer = 0;
        int totalQuestion = 0;
        int wrongAnswer = 0;
        int attemptQuestion =0;

        //TOTAL QUESTIONS SIZE
        totalQuestion  = wrongAnswer = questionAnswerTempModels.size();
        try {
            for(QuestionAnswerTempModel qatm : questionAnswerTempModels )
            {
                if(this.questionAnswerRepository.findById(qatm.getId()).get().getAnsKey().trim().equals(qatm.getGivenAnswer()))
                {
                    correctAnswer++;
                    wrongAnswer--;
                }
                //COUNT ATTEMPT QUESTIONS
                if(qatm.getGivenAnswer() != null)
                {
                    attemptQuestion++;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Get Percentage
        float percentage = (correctAnswer / (float)totalQuestion * 100);

        ResultModel resultModel =new ResultModel();
        resultModel.setCorrectAnswer(correctAnswer);
        resultModel.setWrongAnswer(wrongAnswer);
        resultModel.setTotalQuestion(attemptQuestion);
        resultModel.setAttemptQuestions(attemptQuestion);
        resultModel.setPercentage(percentage);
        resultModel.setTimeDuration(timeDuration);

        //SEND EMAIL
        try {
           if( EmailSender.sendEmail(email,getReportTemplate(resultModel)))
               System.out.println("Email Send Success !!!");
           else
               throw new Exception("Email Not Send");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resultModel;
    }


    public  String getReportTemplate(  ResultModel resultModel )
    {
//      try {
//          FileResource resource = fileResourceRepository.findAll().get(0);
//          if(resource.getUrl() == null || resource.getUrl().isEmpty())
//          {
//              resource.setUrl("https://img.freepik.com/free-vector/studying-concept-illustration_114360-1301.jpg?w=2000");
//          }
//      }
//      catch (Exception e)
//      {
//          e.printStackTrace();
//      }
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
                "src="+"https://images.unsplash.com/photo-1649859394657-8762d8a4758a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1552&q=80"+" " +
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
                "       <p class=\"cl200\">" +
                ""+resultModel.getAttemptQuestions()+"" +
                "</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"main\">\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"left\">\n" +
                "      <p class=\"cl100\">CORRECT-ANSWER</p>\n" +
                "       <p class=\"cl200\">" +
                ""+resultModel.getCorrectAnswer()+"" +
                "</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"cl400\">\n" +
                "    <div class=\"left\">\n" +
                "  <p class=\"cl100\">PERCENTAGE</p>\n" +
                "   <p class=\"cl200\">" +
                ""+resultModel.getPercentage()+"" +
                "</p>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"main\">\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"left\">\n" +
                "  <p class=\"cl100\">TOTAL-ANSWER</p>\n" +
                "   <p class=\"cl200\">" +
                ""+resultModel.getCorrectAnswer()+"" +
                "</p>\n" +
                " </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"cl500\">\n" +
                "    <div class=\"left\">\n" +
                "  <p class=\"cl100\">WRONG ANSWERS</p>\n" +
                "   <p class=\"cl200\">" +
                ""+resultModel.getWrongAnswer()+"" +
                "</p>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"main\">\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"left\">\n" +
                "  <p class=\"cl100\">  TIME DURATION</p>\n" +
                "   <p class=\"cl200\">" +
                ""+resultModel.getTimeDuration()+"" +
                "</p>\n" +
                " </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
    }

}
