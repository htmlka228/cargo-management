package com.example.application.view;

import com.example.application.entity.CargoStatus;
import com.example.application.repository.CargoStatusRepository;
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

@PageTitle("Статусы груза")
@Route(value = "statuses", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class CargoStatusView extends VerticalLayout {
    private final CargoStatusRepository cargoStatusRepository;

    private final Grid<CargoStatus> cargoStatusGrid = new Grid<>(CargoStatus.class, false);
    private final TextField filter = new TextField("", "Поиск статуса");
    private final Button addNewBtn = new Button("Добавить новый статус");
    private final Button changeBtn = new Button("Изменить свойства статуса");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, cargoStatusGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<CargoStatus> cargoStatuses = cargoStatusRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            cargoStatuses = cargoStatuses.stream()
                    .filter(cargoStatus -> StringUtils.containsIgnoreCase(String.valueOf(cargoStatus.getId()), filter)
                            || StringUtils.containsIgnoreCase(String.valueOf(cargoStatus.getStatusCode()), filter)
                            || StringUtils.containsIgnoreCase(cargoStatus.getStatusName(), filter)
                            || StringUtils.containsIgnoreCase(cargoStatus.getDescription(), filter)
                    )
                    .collect(Collectors.toList());
        }

        cargoStatusGrid.setItems(cargoStatuses);
    }

    private void configureGrid() {
        cargoStatusGrid.setSizeFull();
        cargoStatusGrid.addColumn("id").setAutoWidth(true);
        cargoStatusGrid.addColumn("statusCode").setHeader("Код статуса").setAutoWidth(true);
        cargoStatusGrid.addColumn("statusName").setHeader("Название статуса").setAutoWidth(true);
        cargoStatusGrid.addColumn("description").setHeader("Описание").setAutoWidth(true);
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
