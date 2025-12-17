package com.example.flashcardcuoiky;

import java.util.ArrayList;
import java.util.List;

public class VocabularyHelper {
    
    public static class Question {
        public String term;
        public String phonetic;
        public String translation;
        public String[] options;
        public int correctAnswer;

        public Question(String term, String phonetic, String translation, String[] options, int correctAnswer) {
            this.term = term;
            this.phonetic = phonetic;
            this.translation = translation;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
    
    public static class ReadingQuestion {
        public String passage;
        public String question;
        public String[] options;
        public int correctAnswer;

        public ReadingQuestion(String passage, String question, String[] options, int correctAnswer) {
            this.passage = passage;
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
    
    public static class WritingQuestion {
        public String phonetic;
        public String translation;
        public String correctAnswer;

        public WritingQuestion(String phonetic, String translation, String correctAnswer) {
            this.phonetic = phonetic;
            this.translation = translation;
            this.correctAnswer = correctAnswer;
        }
    }
    
    public static List<Question> getAnimalVocabulary() {
        List<Question> questions = new ArrayList<>();
        
        // Từ vựng về động vật
        questions.add(new Question("Dog", "/dɔːg/", "Con chó", 
            new String[]{"Con mèo", "Con chó", "Con gà", "Con lợn"}, 1));
        
        questions.add(new Question("Cat", "/kæt/", "Con mèo", 
            new String[]{"Con mèo", "Con chó", "Con thỏ", "Con chuột"}, 0));
        
        questions.add(new Question("Bird", "/bɜːrd/", "Con chim", 
            new String[]{"Con cá", "Con chim", "Con bướm", "Con ong"}, 1));
        
        questions.add(new Question("Fish", "/fɪʃ/", "Con cá", 
            new String[]{"Con cá", "Con tôm", "Con cua", "Con ốc"}, 0));
        
        questions.add(new Question("Rabbit", "/'ræbɪt/", "Con thỏ", 
            new String[]{"Con chuột", "Con thỏ", "Con sóc", "Con nhím"}, 1));
        
        questions.add(new Question("Mouse", "/maʊs/", "Con chuột", 
            new String[]{"Con chuột", "Con thỏ", "Con sóc", "Con nhím"}, 0));
        
        questions.add(new Question("Horse", "/hɔːrs/", "Con ngựa", 
            new String[]{"Con bò", "Con ngựa", "Con dê", "Con cừu"}, 1));
        
        questions.add(new Question("Cow", "/kaʊ/", "Con bò", 
            new String[]{"Con bò", "Con trâu", "Con dê", "Con cừu"}, 0));
        
        questions.add(new Question("Pig", "/pɪg/", "Con lợn", 
            new String[]{"Con lợn", "Con bò", "Con dê", "Con cừu"}, 0));
        
        questions.add(new Question("Chicken", "/'tʃɪkɪn/", "Con gà", 
            new String[]{"Con vịt", "Con gà", "Con ngỗng", "Con chim"}, 1));
        
        questions.add(new Question("Duck", "/dʌk/", "Con vịt", 
            new String[]{"Con vịt", "Con gà", "Con ngỗng", "Con chim"}, 0));
        
        questions.add(new Question("Elephant", "/'elɪfənt/", "Con voi", 
            new String[]{"Con hổ", "Con sư tử", "Con voi", "Con gấu"}, 2));
        
        questions.add(new Question("Lion", "/'laɪən/", "Con sư tử", 
            new String[]{"Con hổ", "Con sư tử", "Con báo", "Con gấu"}, 1));
        
        questions.add(new Question("Tiger", "/'taɪgər/", "Con hổ", 
            new String[]{"Con hổ", "Con sư tử", "Con báo", "Con gấu"}, 0));
        
        questions.add(new Question("Bear", "/beər/", "Con gấu", 
            new String[]{"Con sói", "Con cáo", "Con gấu", "Con hươu"}, 2));
        
        questions.add(new Question("Wolf", "/wʊlf/", "Con sói", 
            new String[]{"Con sói", "Con cáo", "Con chó", "Con mèo"}, 0));
        
        questions.add(new Question("Fox", "/fɑːks/", "Con cáo", 
            new String[]{"Con sói", "Con cáo", "Con chó", "Con mèo"}, 1));
        
        questions.add(new Question("Deer", "/dɪər/", "Con hươu", 
            new String[]{"Con nai", "Con hươu", "Con dê", "Con cừu"}, 1));
        
        questions.add(new Question("Monkey", "/'mʌŋki/", "Con khỉ", 
            new String[]{"Con khỉ", "Con vượn", "Con gấu", "Con hổ"}, 0));
        
        questions.add(new Question("Snake", "/sneɪk/", "Con rắn", 
            new String[]{"Con rắn", "Con thằn lằn", "Con ếch", "Con cá"}, 0));
        
        questions.add(new Question("Frog", "/frɑːg/", "Con ếch", 
            new String[]{"Con ếch", "Con cóc", "Con rắn", "Con cá"}, 0));
        
        questions.add(new Question("Butterfly", "/'bʌtərflaɪ/", "Con bướm", 
            new String[]{"Con ong", "Con bướm", "Con kiến", "Con nhện"}, 1));
        
        questions.add(new Question("Bee", "/biː/", "Con ong", 
            new String[]{"Con ong", "Con bướm", "Con kiến", "Con nhện"}, 0));
        
        questions.add(new Question("Spider", "/'spaɪdər/", "Con nhện", 
            new String[]{"Con kiến", "Con nhện", "Con ong", "Con bướm"}, 1));
        
        questions.add(new Question("Ant", "/ænt/", "Con kiến", 
            new String[]{"Con kiến", "Con nhện", "Con ong", "Con bướm"}, 0));
        
        questions.add(new Question("Sheep", "/ʃiːp/", "Con cừu", 
            new String[]{"Con dê", "Con cừu", "Con bò", "Con lợn"}, 1));
        
        questions.add(new Question("Goat", "/goʊt/", "Con dê", 
            new String[]{"Con dê", "Con cừu", "Con bò", "Con lợn"}, 0));
        
        questions.add(new Question("Donkey", "/'dɑːŋki/", "Con lừa", 
            new String[]{"Con lừa", "Con ngựa", "Con bò", "Con trâu"}, 0));
        
        questions.add(new Question("Camel", "/'kæməl/", "Con lạc đà", 
            new String[]{"Con ngựa", "Con lạc đà", "Con bò", "Con dê"}, 1));
        
        questions.add(new Question("Kangaroo", "/ˌkæŋgə'ruː/", "Con kangaroo", 
            new String[]{"Con koala", "Con kangaroo", "Con gấu túi", "Con thú có túi"}, 1));
        
        questions.add(new Question("Panda", "/'pændə/", "Con gấu trúc", 
            new String[]{"Con gấu", "Con gấu trúc", "Con hổ", "Con sư tử"}, 1));
        
        questions.add(new Question("Penguin", "/'pengwɪn/", "Con chim cánh cụt", 
            new String[]{"Con chim", "Con chim cánh cụt", "Con vịt", "Con ngỗng"}, 1));
        
        questions.add(new Question("Dolphin", "/'dɑːlfɪn/", "Con cá heo", 
            new String[]{"Con cá", "Con cá heo", "Con cá mập", "Con cá voi"}, 1));
        
        questions.add(new Question("Whale", "/weɪl/", "Con cá voi", 
            new String[]{"Con cá heo", "Con cá voi", "Con cá mập", "Con cá"}, 1));
        
        questions.add(new Question("Shark", "/ʃɑːrk/", "Con cá mập", 
            new String[]{"Con cá mập", "Con cá heo", "Con cá voi", "Con cá"}, 0));
        
        questions.add(new Question("Turtle", "/'tɜːrtəl/", "Con rùa", 
            new String[]{"Con rùa", "Con cá", "Con ếch", "Con thằn lằn"}, 0));
        
        questions.add(new Question("Crocodile", "/'krɑːkədaɪl/", "Con cá sấu", 
            new String[]{"Con rắn", "Con cá sấu", "Con thằn lằn", "Con rùa"}, 1));
        
        questions.add(new Question("Eagle", "/'iːgəl/", "Con đại bàng", 
            new String[]{"Con chim", "Con đại bàng", "Con cú", "Con quạ"}, 1));
        
        questions.add(new Question("Owl", "/aʊl/", "Con cú", 
            new String[]{"Con chim", "Con cú", "Con đại bàng", "Con quạ"}, 1));
        
        questions.add(new Question("Crow", "/kroʊ/", "Con quạ", 
            new String[]{"Con chim", "Con quạ", "Con cú", "Con đại bàng"}, 1));
        
        questions.add(new Question("Parrot", "/'pærət/", "Con vẹt", 
            new String[]{"Con chim", "Con vẹt", "Con đại bàng", "Con cú"}, 1));
        
        questions.add(new Question("Peacock", "/'piːkɑːk/", "Con công", 
            new String[]{"Con chim", "Con công", "Con vẹt", "Con đại bàng"}, 1));
        
        questions.add(new Question("Swan", "/swɑːn/", "Con thiên nga", 
            new String[]{"Con vịt", "Con thiên nga", "Con ngỗng", "Con chim"}, 1));
        
        questions.add(new Question("Goose", "/guːs/", "Con ngỗng", 
            new String[]{"Con vịt", "Con ngỗng", "Con thiên nga", "Con gà"}, 1));
        
        questions.add(new Question("Rooster", "/'ruːstər/", "Con gà trống", 
            new String[]{"Con gà", "Con gà trống", "Con gà mái", "Con vịt"}, 1));
        
        questions.add(new Question("Hen", "/hen/", "Con gà mái", 
            new String[]{"Con gà", "Con gà mái", "Con gà trống", "Con vịt"}, 1));
        
        questions.add(new Question("Zebra", "/'ziːbrə/", "Con ngựa vằn", 
            new String[]{"Con ngựa", "Con ngựa vằn", "Con lừa", "Con bò"}, 1));
        
        return questions;
    }
    
    public static List<ReadingQuestion> getAnimalReadingQuestions() {
        List<ReadingQuestion> questions = new ArrayList<>();
        List<Question> vocab = getAnimalVocabulary();
        
        // Tạo câu hỏi đọc hiểu dựa trên từ vựng động vật
        for (int i = 0; i < Math.min(vocab.size(), 20); i++) {
            Question q = vocab.get(i);
            questions.add(new ReadingQuestion(
                "The " + q.term.toLowerCase() + " is a common animal. " + 
                "It is known for its " + q.translation.toLowerCase() + ". " +
                "Many people like to see " + q.term.toLowerCase() + "s in nature.",
                "What animal is described?",
                new String[]{"Dog", "Cat", q.term, "Bird"}, 2));
        }
        
        return questions;
    }
    
    public static List<WritingQuestion> getAnimalWritingQuestions() {
        List<WritingQuestion> questions = new ArrayList<>();
        List<Question> vocab = getAnimalVocabulary();
        
        // Tạo câu hỏi viết dựa trên từ vựng động vật
        for (Question q : vocab) {
            questions.add(new WritingQuestion(q.phonetic, q.translation, q.term));
        }
        
        return questions;
    }
    
    public static List<Question> getDefaultVocabulary() {
        List<Question> questions = new ArrayList<>();
        
        questions.add(new Question("Cabinet", "/'kæbinit/", "Tủ", 
            new String[]{"Toaster", "Dishwasher /'diʃ, wɔ:tə/: Máy rửa bát", "Oven", "Cabinet"}, 3));
        
        questions.add(new Question("Mix", "/mıks/", "Trộn", 
            new String[]{"Vỉ nướng", "đánh", "trộn", "/'miksə/: Máy trộn"}, 2));
        
        questions.add(new Question("Pour", "/po:r/", "Đổ", 
            new String[]{"Đổ", "Rót", "Trộn", "Nấu"}, 0));
        
        questions.add(new Question("Stir", "/stɜ:/", "Khuấy", 
            new String[]{"Khuấy", "Đổ", "Trộn", "Nấu"}, 0));
        
        questions.add(new Question("Bake", "/beık/", "Nướng", 
            new String[]{"Nướng", "Rang", "Luộc", "Chiên"}, 0));
        
        questions.add(new Question("Boil", "/bɔıl/", "Đun sôi", 
            new String[]{"Đun sôi", "Nướng", "Chiên", "Hấp"}, 0));
        
        questions.add(new Question("Fry", "/fraı/", "Chiên", 
            new String[]{"Chiên", "Nướng", "Luộc", "Hấp"}, 0));
        
        questions.add(new Question("Grill", "/grıl/", "Nướng", 
            new String[]{"Nướng", "Chiên", "Luộc", "Hấp"}, 0));
        
        questions.add(new Question("Steam", "/sti:m/", "Hấp", 
            new String[]{"Hấp", "Nướng", "Chiên", "Luộc"}, 0));
        
        questions.add(new Question("Roast", "/roʊst/", "Quay", 
            new String[]{"Quay", "Nướng", "Chiên", "Luộc"}, 0));
        
        questions.add(new Question("Slice", "/slaıs/", "Cắt lát", 
            new String[]{"Cắt lát", "Băm", "Thái", "Cắt nhỏ"}, 0));
        
        questions.add(new Question("Chop", "/tʃɑ:p/", "Băm", 
            new String[]{"Băm", "Cắt lát", "Thái", "Cắt nhỏ"}, 0));
        
        questions.add(new Question("Peel", "/pi:l/", "Gọt vỏ", 
            new String[]{"Gọt vỏ", "Rửa", "Cắt", "Nấu"}, 0));
        
        questions.add(new Question("Dice", "/daıs/", "Cắt hạt lựu", 
            new String[]{"Cắt hạt lựu", "Băm", "Cắt lát", "Thái"}, 0));
        
        questions.add(new Question("Grate", "/greıt/", "Bào", 
            new String[]{"Bào", "Băm", "Cắt", "Thái"}, 0));
        
        questions.add(new Question("Whisk", "/wısk/", "Đánh", 
            new String[]{"Đánh", "Khuấy", "Trộn", "Nấu"}, 0));
        
        questions.add(new Question("Knead", "/ni:d/", "Nhào", 
            new String[]{"Nhào", "Trộn", "Khuấy", "Đánh"}, 0));
        
        questions.add(new Question("Marinate", "/'mærıneıt/", "Ướp", 
            new String[]{"Ướp", "Nấu", "Chiên", "Nướng"}, 0));
        
        questions.add(new Question("Season", "/'si:zən/", "Nêm gia vị", 
            new String[]{"Nêm gia vị", "Nấu", "Chiên", "Nướng"}, 0));
        
        questions.add(new Question("Garnish", "/'gɑ:rnıʃ/", "Trang trí", 
            new String[]{"Trang trí", "Nấu", "Chiên", "Nướng"}, 0));
        
        // Add more questions to reach 147
        for (int i = 20; i < 147; i++) {
            questions.add(new Question("Word" + i, "/wɜ:rd" + i + "/", "Từ " + i, 
                new String[]{"Option A", "Option B", "Option C", "Option D"}, i % 4));
        }
        
        return questions;
    }
}

