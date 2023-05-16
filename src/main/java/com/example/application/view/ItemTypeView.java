package com.example.application.view;

import com.example.application.entity.ItemType;
import com.example.application.repository.ItemTypeRepository;
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

@PageTitle("Типы предметов")
@Route(value = "itemTypes", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class ItemTypeView extends VerticalLayout {
    private final ItemTypeRepository itemTypeRepository;

    private final Grid<ItemType> cargoServiceGrid = new Grid<>(ItemType.class, false);
    private final TextField filter = new TextField("", "Поиск типа предметов");
    private final Button addNewBtn = new Button("Добавить новый тип предмета");
    private final Button changeBtn = new Button("Изменить свойства типа предмета");
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
        List<ItemType> itemTypes = itemTypeRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            itemTypes = itemTypes.stream()
                    .filter(itemType -> StringUtils.containsIgnoreCase(String.valueOf(itemType.getId()), filter)
                            || StringUtils.containsIgnoreCase(itemType.getCode(), filter)
                            || StringUtils.containsIgnoreCase(itemType.getName(), filter)
                            || StringUtils.containsIgnoreCase(itemType.getDescription(), filter)
                    )
                    .collect(Collectors.toList());
        }

        cargoServiceGrid.setItems(itemTypes);
    }

    private void configureGrid() {
        cargoServiceGrid.setSizeFull();
        cargoServiceGrid.addColumn("id").setAutoWidth(true);
        cargoServiceGrid.addColumn("code").setHeader("Код типа предмета").setAutoWidth(true);
        cargoServiceGrid.addColumn("name").setHeader("Тип предмета").setAutoWidth(true);
        cargoServiceGrid.addColumn("description").setHeader("Описание типа предмета").setAutoWidth(true);
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
