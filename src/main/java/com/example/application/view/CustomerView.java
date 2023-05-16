package com.example.application.view;

import com.example.application.entity.Customer;
import com.example.application.repository.CustomerRepository;
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

@PageTitle("Клиенты")
@Route(value = "customers", layout = MainLayout.class)
@RequiredArgsConstructor
@PermitAll
public class CustomerView extends VerticalLayout {
    private final CustomerRepository customerRepository;

    private final Grid<Customer> cargoServiceGrid = new Grid<>(Customer.class, false);
    private final TextField filter = new TextField("", "Поиск клиента");
    private final Button addNewBtn = new Button("Добавить нового клиента");
    private final Button changeBtn = new Button("Изменить данные клиента");
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
        List<Customer> customers = customerRepository.findAll();

        if (StringUtils.isNotBlank(filter)) {
            customers = customers.stream()
                    .filter(customer -> StringUtils.containsIgnoreCase(customer.getId().toString(), filter)
                            || StringUtils.containsIgnoreCase(customer.getFirstname(), filter)
                            || StringUtils.containsIgnoreCase(customer.getLastname(), filter)
                            || StringUtils.containsIgnoreCase(customer.getPatronymic(), filter)
                            || StringUtils.containsIgnoreCase(customer.getAddress(), filter)
                            || StringUtils.containsIgnoreCase(customer.getPhone(), filter)
                            || StringUtils.containsIgnoreCase(customer.getAdditionalPhone(), filter)
                    )
                    .collect(Collectors.toList());
        }

        cargoServiceGrid.setItems(customers);
    }

    private void configureGrid() {
        cargoServiceGrid.setSizeFull();
        cargoServiceGrid.setColumns("id", "lastname", "firstname", "patronymic", "address", "phone", "additionalPhone");
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
