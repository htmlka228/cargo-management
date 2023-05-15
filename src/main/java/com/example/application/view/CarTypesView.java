package com.example.application.view;

import com.example.application.entity.CarType;
import com.example.application.repository.CarTypeRepository;
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

@PageTitle("Доступные типы машин")
@Route(value = "cars", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class CarTypesView extends VerticalLayout {
    private final CarTypeRepository carTypeRepository;

    private final Grid<CarType> carTypeGrid = new Grid<>(CarType.class, false);
    private final TextField filter = new TextField("", "Поиск машин");
    private final Button addNewBtn = new Button("Добавить новый тип машин");
    private final Button changeBtn = new Button("Изменить свойства выбраного типа машины");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, carTypeGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<CarType> carTypes = carTypeRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            carTypes = carTypes.stream()
                    .filter(carType -> StringUtils.containsIgnoreCase(String.valueOf(carType.getId()), filter)
                            || StringUtils.containsIgnoreCase(carType.getCode(), filter)
                            || StringUtils.containsIgnoreCase(carType.getName(), filter)
                            || StringUtils.containsIgnoreCase(String.valueOf(carType.getMaxWeight()), filter)
                    )
                    .collect(Collectors.toList());
        }

        carTypeGrid.setItems(carTypes);
    }

    private void configureGrid() {
        carTypeGrid.setSizeFull();
        carTypeGrid.setColumns("id", "code", "name", "maxWeight");
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
