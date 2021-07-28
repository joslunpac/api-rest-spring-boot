package com.apirestspringboot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

@Component
public class Utilidades {

	/**
	 * Recupera una lista orders
	 *
	 * @param sort Array de ordenaciones
	 * @return Listado de orders
	 */
	public List<Order> construirOrders(String[] sort) {
		List<Order> orders = new ArrayList<>();

		if (sort[0].contains(",")) {
			// Cuando se ordena por mas de una columna
			for (String sortOrder : sort) {
				String[] sort2 = sortOrder.split(",");
				orders.add(new Order(getSortDirection(sort2[1]), sort2[0]));
			}
		} else {
			// Cuando se ordena por una columna
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}

		return orders;
	}

	/**
	 * Convertimos "asc/desc" en "Sort.Direction.ASC/Sort.Direction.DES"
	 *
	 * @param String Dirección de la ordenación
	 * @return Dirección de la ordenación
	 */
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

}
