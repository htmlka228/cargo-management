package com.example.application.view;

import com.example.application.entity.Role;
import com.example.application.repository.RoleRepository;
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

@PageTitle("Роли пользователей")
@Route(value = "roles", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class RoleView extends VerticalLayout {
    private final RoleRepository roleRepository;

    private final Grid<Role> roleGrid = new Grid<>(Role.class, false);
    private final TextField filter = new TextField("", "Поиск роли");
    private final Button addNewBtn = new Button("Добавить новую роль");
    private final Button changeBtn = new Button("Изменить свойства роли");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, roleGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<Role> roles = roleRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            roles = roles.stream()
                    .filter(role -> StringUtils.containsIgnoreCase(String.valueOf(role.getId()), filter)
                            || StringUtils.containsIgnoreCase(role.getName(), filter)
                            || StringUtils.containsIgnoreCase(role.getDescription(), filter)
                    )
                    .collect(Collectors.toList());
        }

        roleGrid.setItems(roles);
    }

    private void configureGrid() {
        roleGrid.setSizeFull();
        roleGrid.addColumn("id").setAutoWidth(true);
        roleGrid.addColumn("name").setHeader("Название роли").setAutoWidth(true);
        roleGrid.addColumn("description").setHeader("Описание роли").setAutoWidth(true);
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
