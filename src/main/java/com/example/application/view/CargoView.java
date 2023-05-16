package com.example.application.view;

import com.example.application.entity.Cargo;
import com.example.application.repository.CargoRepository;
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

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@PageTitle("Грузы")
@Route(value = "cargos", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class CargoView extends VerticalLayout {
    private final CargoRepository cargoRepository;

    private final Grid<Cargo> cargoGrid = new Grid<>(Cargo.class, false);
    private final TextField filter = new TextField("", "Поиск груза");
    private final Button addNewBtn = new Button("Создать новый груз");
    private final Button changeBtn = new Button("Изменить свойства груза");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, cargoGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<Cargo> cargos = cargoRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            cargos = cargos.stream()
                    .filter(cargo -> StringUtils.containsIgnoreCase(cargo.getId().toString(), filter)
                            || StringUtils.containsIgnoreCase(cargo.getCargoIndex(), filter)
                            || StringUtils.containsIgnoreCase(String.valueOf(cargo.getTotalPrice()), filter)
                            || StringUtils.containsIgnoreCase(cargo.getLocationFrom(), filter)
                            || StringUtils.containsIgnoreCase(cargo.getLocationTo(), filter)
                    )
                    .collect(Collectors.toList());
        }

        cargoGrid.setItems(cargos);
    }

    private void configureGrid() {
        cargoGrid.setSizeFull();
        cargoGrid.addColumn("id").setAutoWidth(true);
        cargoGrid.addColumn("cargoIndex").setHeader("Индекс груза").setAutoWidth(true);
        cargoGrid.addColumn("totalPrice").setHeader("Стоимость").setAutoWidth(true);
        cargoGrid.addColumn(cargo -> cargo.getCustomer().getFullName()).setHeader("Клиент").setAutoWidth(true);
        cargoGrid.addColumn(cargo -> cargo.getCurrentStatus().getStatusCode()).setHeader("Статус груза").setAutoWidth(true);
        cargoGrid.addColumn(cargo -> cargo.getCurrency().getCode()).setHeader("Валюта").setAutoWidth(true);
        cargoGrid.addColumn(cargo -> cargo.getCreatedAt().format(DateTimeFormatter.ofPattern(
                "dd MMMM yyyy HH:mm:ss",
                new Locale("ru", "RU")))
        ).setHeader("Дата создания").setAutoWidth(true);
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
