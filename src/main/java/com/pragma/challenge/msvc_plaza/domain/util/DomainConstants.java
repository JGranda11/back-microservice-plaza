package com.pragma.challenge.msvc_plaza.domain.util;

import com.pragma.challenge.msvc_plaza.domain.util.enums.OrderState;

import java.util.List;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility Class");
    }

    // STUFF
    public static final Integer PIN_LENGTH = 8;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final List<OrderState> PROCESS_STATES = List.of(
            OrderState.WAITING,
            OrderState.PREPARING,
            OrderState.DONE
    );

    // Fields
    public static final String NAME_FIELD = "name";

    // Pagination
    public static final Integer PAGE_SIZE = 10;

    public static final String NOTIFICATION_MESSAGE_TEMPLATE
            = "¡Hola, %s %s! Tu pedido está listo, puedes recogerlo dando el siguiente código en el local: %s";

}
