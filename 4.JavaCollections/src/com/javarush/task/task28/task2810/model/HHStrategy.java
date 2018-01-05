package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int i = 0;
        while (true) {
            try {
                Document document = getDocument(searchString, i);
                Elements elements = document.select("[data-qa='vacancy-serp__vacancy']");
                if (!elements.isEmpty()) {
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.select("[data-qa='vacancy-serp__vacancy-title']").text());
                        vacancy.setUrl(element.select("[data-qa='vacancy-serp__vacancy-title']").attr("href"));
                        vacancy.setCompanyName(element.select("[data-qa='vacancy-serp__vacancy-employer']").text());
                        vacancy.setCity(element.select("[data-qa='vacancy-serp__vacancy-address']").text());
                        vacancy.setSalary(element.select("[data-qa='vacancy-serp__vacancy-compensation']").text());
                        if (vacancy.getSalary() == null)
                            vacancy.setSalary("");
                        vacancy.setSiteName("https://hh.ua");
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
