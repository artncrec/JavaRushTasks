package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int i = 0;
        while (true) {
            try {
                Document document = getDocument(searchString, i);
                Elements elements = document.select("[class='job']");
                elements.addAll(document.select("[class='job marked']"));
                if (!elements.isEmpty()) {
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.getElementsByClass("title").text());
                        vacancy.setSiteName("https://moikrug.ru");
                        vacancy.setUrl(vacancy.getSiteName() + element.select("a").first().attr("href"));
                        vacancy.setCompanyName(element.getElementsByClass("company_name").text());
                        vacancy.setCity(element.getElementsByClass("location").text());
                        vacancy.setSalary(element.getElementsByClass("salary").text());
                        if (vacancy.getSalary() == null)
                            vacancy.setSalary("");
                        vacancies.add(vacancy);
                    }
                }
                else
                    break;
                i++;
            } catch (IOException e) {
            }
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Amigo").referrer("").get();
    }
}
