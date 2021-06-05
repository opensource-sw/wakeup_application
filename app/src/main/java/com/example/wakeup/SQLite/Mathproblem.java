package com.example.wakeup.SQLite;

public class MathProblem {
    public String Question[]={  //문제질문
            "4 ÷ 0.5=?",

            "2 + 2 x 2 ÷ 4=? ",

            "  A + A + A=30\n" +
                    "  A + B + B=20\n" +
                    "  B + C + C=13\n"+
                    "  A + B x C=?",

            "3 + 3 x 3 + 3=?",

            "½ + ½=?",

            "9 - 3 x ⅓ + 1=?",

            "  37 - A=18\n"+
                    "      A=?",

            "2 + 5 + 7=?",

            "4 x 0.25=?",

            "1 x 0 x 1 + 2=?"

    };

    public String Button[][]={    //버튼
            {"2", "4", "6", "8"},
            {"3", "6", "4", "2"},
            {"60", "19", "30", "10"},
            {"12", "15", "27", "21"},
            {"½","0.25","1","2"},
            {"1","3","6","9"},
            {"9","15","18","19"},
            {"12","13","14","15"},
            {"1.0","4.5","5.0","10.0"},
            {"0","1","2","3"}
    };

    private String Correct[]={"8", "3", "30", "15","1","9","19","14","1.0","2"};  //정답

    public String getQuestion(int a){
        String Qusetions= Question[a];
        return Qusetions;
    }

    public String getButton1(int a){
        String Choices=Button[a][0];
        return Choices;
    }

    public String getButton2(int a){
        String Choices=Button[a][1];
        return Choices;
    }

    public String getButton3(int a){
        String Choices=Button[a][2];
        return Choices;
    }

    public String getButton4(int a){
        String Choices=Button[a][3];
        return Choices;
    }

    public String getCorrect(int a){
        String Corrects=Correct[a];
        return Corrects;
    }
}
