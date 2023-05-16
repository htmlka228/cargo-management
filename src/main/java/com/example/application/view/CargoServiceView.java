package com.example.application.view;

import com.example.application.entity.CargoService;
import com.example.application.repository.CargoServiceRepository;
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

@PageTitle("Доступные сервисы для груза")
@Route(value = "services", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class CargoServiceView extends VerticalLayout {
    private final CargoServiceRepository cargoServiceRepository;

    private final Grid<CargoService> cargoServiceGrid = new Grid<>(CargoService.class, false);
    private final TextField filter = new TextField("", "Поиск сервисов");
    private final Button addNewBtn = new Button("Добавить новый сервис");
    private final Button changeBtn = new Button("Изменить свойства выбраного сервиса");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, cargoServiceGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<CargoService> cargoServices = cargoServiceRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            cargoServices = cargoServices.stream()
                    .filter(cargoService -> StringUtils.containsIgnoreCase(String.valueOf(cargoService.getId()), filter)
                            || StringUtils.containsIgnoreCase(cargoService.getCode(), filter)
                            || StringUtils.containsIgnoreCase(cargoService.getName(), filter)
                            || StringUtils.containsIgnoreCase(String.valueOf(cargoService.getPrice()), filter)
                            || StringUtils.containsIgnoreCase(cargoService.getDescription(), filter)
                    )
                    .collect(Collectors.toList());
        }

        cargoServiceGrid.setItems(cargoServices);
    }

    private void configureGrid() {
        cargoServiceGrid.setSizeFull();
        cargoServiceGrid.addColumn("id").setAutoWidth(true);
        cargoServiceGrid.addColumn("code").setHeader("Код сервиса").setAutoWidth(true);
        cargoServiceGrid.addColumn("name").setHeader("Название сервиса").setAutoWidth(true);
        cargoServiceGrid.addColumn("price").setHeader("Цена").setAutoWidth(true);
        cargoServiceGrid.addColumn("description").setHeader("Описание").setAutoWidth(true);
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
