package com.example.application.view;

import com.example.application.entity.Currency;
import com.example.application.repository.CurrencyRepository;
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

@PageTitle("Доступные валюты")
@Route(value = "currencies", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class CurrencyView extends VerticalLayout {
    private final CurrencyRepository currencyRepository;

    private final Grid<Currency> currencyGrid = new Grid<>(Currency.class, false);
    private final TextField filter = new TextField("", "Поиск валюты");
    private final Button addNewBtn = new Button("Добавить новую валюту");
    private final Button changeBtn = new Button("Изменить свойства выбранной валюты");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, currencyGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<Currency> currencies = currencyRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            currencies = currencies.stream()
                    .filter(currency -> StringUtils.containsIgnoreCase(String.valueOf(currency.getId()), filter)
                            || StringUtils.containsIgnoreCase(currency.getCode(), filter)
                            || StringUtils.containsIgnoreCase(currency.getCountry(), filter)
                    )
                    .collect(Collectors.toList());
        }

        currencyGrid.setItems(currencies);
    }

    private void configureGrid() {
        currencyGrid.setSizeFull();
        currencyGrid.addColumn("id").setAutoWidth(true);
        currencyGrid.addColumn("code").setHeader("Код валюты").setAutoWidth(true);
        currencyGrid.addColumn("country").setHeader("Страна").setAutoWidth(true);
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
