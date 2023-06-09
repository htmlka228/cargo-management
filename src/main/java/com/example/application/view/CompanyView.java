package com.example.application.view;

import com.example.application.entity.Company;
import com.example.application.repository.CompanyRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Партнёры компании")
@Route(value = "companies", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class CompanyView extends VerticalLayout {
    private final CompanyRepository companyRepository;

    private final Grid<Company> companyGrid = new Grid<>(Company.class, false);
    private final TextField filter = new TextField("", "Поиск компаний");
    private final Button addNewBtn = new Button("Добавить новую компанию-партнёра");
    private final Button changeBtn = new Button("Изменить свойства выбранной компании");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, companyGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<Company> companies = companyRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            companies = companies.stream()
                    .filter(company -> StringUtils.containsIgnoreCase(company.getId().toString(), filter)
                            || StringUtils.containsIgnoreCase(company.getCode(), filter)
                            || StringUtils.containsIgnoreCase(company.getName(), filter)
                            || StringUtils.containsIgnoreCase(String.valueOf(company.getPhone()), filter)
                            || StringUtils.containsIgnoreCase(company.getEmail(), filter)
                            || StringUtils.containsIgnoreCase(company.getCountry(), filter)
                            || StringUtils.containsIgnoreCase(company.getAddress(), filter)
                            || StringUtils.containsIgnoreCase(company.getZipcode(), filter)
                            || StringUtils.containsIgnoreCase(company.getInn(), filter)
                            || StringUtils.containsIgnoreCase(company.getKpp(), filter)
                    )
                    .collect(Collectors.toList());
        }

        companyGrid.setItems(companies);
    }

    private void configureGrid() {
        companyGrid.setSizeFull();
        companyGrid.addColumn("id").setAutoWidth(true);
        companyGrid.addColumn("code").setHeader("Код компании").setAutoWidth(true);
        companyGrid.addColumn("name").setHeader("Название компании").setAutoWidth(true);
        companyGrid.addColumn("phone").setHeader("Телефон").setAutoWidth(true);
        companyGrid.addColumn("email").setHeader("Электронная почта").setAutoWidth(true);
        companyGrid.addColumn("country").setHeader("Страна").setAutoWidth(true);
        companyGrid.addColumn("address").setHeader("Адрес").setAutoWidth(true);
        companyGrid.addColumn("zipcode").setHeader("Почтовый индекс").setAutoWidth(true);
        companyGrid.addColumn("inn").setHeader("ИНН").setAutoWidth(true);
        companyGrid.addColumn("kpp").setHeader("КПП").setAutoWidth(true);
    }

    private void addButtonListeners() {
//        addNewBtn.addClickListener(e -> gameEditor.editGame(new Game()));

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> show(e.getValue()));

//        changeBtn.addClickListener(e -> {
//            Game game = grid.asSingleSelect().getValue();
//
//            if (game == null) {
//                DefaultDialogs.generateErrorDialog("Game hasn't been selected").open();
//            } else {
//                gameEditor.editGame(game);
//            }
//        });
    }
}
