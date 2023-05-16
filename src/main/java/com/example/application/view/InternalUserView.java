package com.example.application.view;

import com.example.application.entity.Role;
import com.example.application.entity.User;
import com.example.application.repository.UserRepository;
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

@PageTitle("Пользователи информационной системы")
@Route(value = "users", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class InternalUserView extends VerticalLayout {
    private final UserRepository userRepository;

    private final Grid<User> userGrid = new Grid<>(User.class, false);
    private final TextField filter = new TextField("", "Поиск пользователя");
    private final Button addNewBtn = new Button("Добавить нового пользователя");
    private final Button changeBtn = new Button("Изменить свойства пользователя");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn, changeBtn);

    @PostConstruct
    public void init() {
        setSizeFull();
        configureGrid();
        addButtonListeners();
        add(toolbar, userGrid);

        show();
    }

    private void show() {
        show(StringUtils.EMPTY);
    }

    private void show(String filter) {
        List<User> users = userRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            users = users.stream()
                    .filter(user -> StringUtils.containsIgnoreCase(user.getId().toString(), filter)
                            || StringUtils.containsIgnoreCase(user.getUsername(), filter)
                            || StringUtils.containsIgnoreCase(user.getFirstname(), filter)
                            || StringUtils.containsIgnoreCase(user.getLastname(), filter)
                            || StringUtils.containsIgnoreCase(user.getPatronymic(), filter)
                            || user.getRoles().stream().anyMatch(role -> StringUtils.containsIgnoreCase(role.getName(), filter))
                    )
                    .collect(Collectors.toList());
        }

        userGrid.setItems(users);
    }

    private void configureGrid() {
        userGrid.setSizeFull();
        userGrid.addColumn("id").setAutoWidth(true);
        userGrid.addColumn("username").setHeader("Логин").setAutoWidth(true);
        userGrid.addColumn("lastname").setHeader("Фамилия").setAutoWidth(true);
        userGrid.addColumn("firstname").setHeader("Имя").setAutoWidth(true);
        userGrid.addColumn("patronymic").setHeader("Отчество").setAutoWidth(true);
        userGrid.addColumn(user -> user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())).setHeader("Роли").setAutoWidth(true);
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
