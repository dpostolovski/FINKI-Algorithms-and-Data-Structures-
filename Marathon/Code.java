import java.util.Scanner;

public class Test {
    private static class Athlete
    {
        private String name;
        private String gender;
        private int age;
        private Double score;
        private String country;

        public Athlete(String name, String gender, int age, Double score, String country) {
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.score = score;
            this.country = country;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String toString()
        {
            return name + "\n" + age + "\n" + country + "\n" + score;
        }
    }

    private static class Marathon implements IMarathon
    {
        private String country;
        private int year;
        private Athlete[] athletes;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public Athlete[] getAthletes() {
            return athletes;
        }

        public Marathon(String country, int year, Athlete[] athletes) {
            this.country = country;
            this.year = year;
            this.athletes = athletes;
        }

        public void setAthletes(Athlete[] athletes) {
            this.athletes = athletes;
        }

        @Override
        public String toString() {
            String result = country + "\n" + year + "\n";

            for(int i = 0;i < athletes.length; i++)
                result += athletes[i].toString() + "\n";

            return result;
        }

        @Override
        public Athlete bestTime() {
            Athlete best = null;
            double bestTime = 999999;
            for(int i =0; i < athletes.length;i++)
            {
                if(athletes[i].getScore() < bestTime)
                {
                    best = athletes[i];
                    bestTime = athletes[i].getScore();
                }
            }

            return best;
        }

        @Override
        public int AthletesFrom(String s) {
            int result = 0;

            for(int i =0; i < athletes.length;i++)
            {
                if(athletes[i].getCountry().equals(s)) {
                    result++;
                }
            }
            return result;
        }
    }

    private interface IMarathon
    {
        public Athlete bestTime();
        public int AthletesFrom(String s);
    }


    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        Athlete[] atleticari = new Athlete[n];

        String ime;
        String pol;
        int vozrast;
        double vreme;
        String zemja;

        input.nextLine();

        for(int i=0;i<n;i++)
        {
            ime = input.nextLine();
            pol = input.nextLine();
            vozrast = input.nextInt();
            vreme = input.nextDouble();
            input.nextLine();
            zemja = input.nextLine();
            atleticari[i]=new Athlete(ime,pol,vozrast,vreme,zemja);
        }

        String mesto;
        int godina;
        String zemjaP;
        mesto = input.nextLine();
        godina = input.nextInt();
        input.nextLine();

        Marathon m1 = new Marathon(mesto, godina, atleticari);
        System.out.print(m1.toString());

        zemjaP = input.nextLine();
        System.out.println("Prvo mesto: " + m1.bestTime().toString() + "\n");
        System.out.println("Ima vkupno " + m1.AthletesFrom(zemjaP) + " atleticar/i od " + zemjaP);
    }
}
