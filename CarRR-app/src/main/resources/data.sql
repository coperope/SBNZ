-- Rental history
INSERT INTO public.rental_history(id)
VALUES (nextval('rental_history_id_seq'));

-- Search history
-- INSERT INTO public.search_history(id)
-- VALUES (nextval('search_history_id_seq'));

-- Search history
INSERT INTO public.recommendations(id)
VALUES (nextval('recommendations_id_seq'));

-- Customer preferences
INSERT INTO public.customer_preferences(id, ac, door_no, fuel_consumption, mileage_limit, seats_no)
VALUES (nextval('customer_preferences_id_seq'), true, 4, 10, 100, 4);

-- Customer
INSERT INTO public.customer(id, name, surname, email, password, phone_number, is_customer, no_of_rentals, customer_preferences_id, customer_rental_history_id, recommendation_id)
	VALUES (nextval('users_id_seq'), 'Vladimir', 'Lenjin', '1917@gmail.com', '123456', '061265978', true, -1, 1, 1, 1);

-- Owner
INSERT INTO public.users(id, name, surname, email, password, phone_number, is_customer, no_of_rentals)
	VALUES (nextval('users_id_seq'), 'Ernesto', 'Guevara', 'che@gmail.com', '123456', '061265978', false, 9);

-- Categories
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Economy');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Compact');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Intermediate');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Standard');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Full size');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Luxury');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Premium');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Sport');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Coupe');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Passenger van');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Pick up');

-- Tags
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'High fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Low fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Sport engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'High engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Low engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Family car');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Small family car');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Big family car');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Long range');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium range');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Short range');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Unlimited mileage');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'High mileage price');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium mileage price');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Low mileage price');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Automatic');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Manual');

-- Brand
INSERT INTO public.brand(id, name)
	VALUES (nextval('brand_id_seq'), 'BMW');
INSERT INTO public.brand(id, name)
	VALUES (nextval('brand_id_seq'), 'Tesla');
INSERT INTO public.brand(id, name)
	VALUES (nextval('brand_id_seq'), 'Audi');
INSERT INTO public.brand(id, name)
	VALUES (nextval('brand_id_seq'), 'Mercedes');

-- Car Model
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), 'X5', 1);
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), 'X1', 1);
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), '206', 1);
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), 'R8', 3);
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), 'R3', 3);
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), 'S220', 4);
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), 'C501', 4);

-- Fuel
INSERT INTO public.fuel(id, name)
	VALUES (nextval('fuel_id_seq'), 'Diesel');
INSERT INTO public.fuel(id, name)
	VALUES (nextval('fuel_id_seq'), 'Gasoline');
INSERT INTO public.fuel(id, name)
	VALUES (nextval('fuel_id_seq'), 'Electric');

-- Transmission
INSERT INTO public.transmission(id, name)
	VALUES (nextval('transmission_id_seq'), 'Automatic');
INSERT INTO public.transmission(id, name)
	VALUES (nextval('transmission_id_seq'), 'Manual');

-- Vehicle
INSERT INTO public.vehicle( -- BWM X1
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 6, -1, 4, 1, 1, 2, 1, 300, 170, 410, 0, 20, 80 , 3100, 80, 25, 40, 1347, 190);
INSERT INTO public.vehicle( -- BWM X5
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 9, 100, 5, 1, 2, 1, 1, 400, 180, 435, 0.9, 20, 87 , 3200, 130, 250, 50, 1800, 195);
INSERT INTO public.vehicle( -- BWM 206
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 10, -1, 2, 1, 1, 3, 1, 250, 160, 410, 0, 10, 45 , 2950, 130, 60, 40, 1450, 180);
INSERT INTO public.vehicle( -- Audi R8
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 12, 120, 4, 3, 2, 4, 1, 250, 170, 430, 1.3, 31, 135 , 3100, 160, 280, 45, 1650, 178);
INSERT INTO public.vehicle( -- Audi R3
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 2, 11, 150, 2, 3, 2, 5, 2, 150, 155, 415, 0.6, 12, 42 , 2000, 130, 150, 43, 1423, 175);
INSERT INTO public.vehicle( -- Mercedes S220
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 11, 100, 5, 4, 1, 6, 1, 450, 172, 425, 1.5, 20, 95 , 3200, 150, 125, 50, 1647, 185);
INSERT INTO public.vehicle( -- Mercedes C501
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 8, -1, 4, 4, 2, 7, 2, 320, 175, 430, 0, 31, 120 , 3000, 100, 25, 40, 1600, 185);
INSERT INTO public.vehicle( -- BWM X1
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 9, 150, 5, 1, 2, 2, 2, 350, 165, 415, 0.8, 10, 45 , 3100, 120, 115, 50, 1547, 195);
INSERT INTO public.vehicle( -- BWM X5
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 13, 100, 5, 1, 1, 1, 1, 450, 180, 435, 1.3, 28, 126 , 3200, 150, 310, 45, 1747, 198);
INSERT INTO public.vehicle( -- BMW 206
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 1, true, 4, 7, 150, 4, 1, 2, 3, 2, 250, 165, 402, 0.4, 20, 88 , 3100, 111, 22, 40, 1547, 190);
