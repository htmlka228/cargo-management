package com.example.application.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Main Page")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class MainView extends VerticalLayout {
    public MainView() {
        H1 helloText = new H1("Hello user. This is main view.");
        setSizeFull();
        add(helloText);
    }
}
