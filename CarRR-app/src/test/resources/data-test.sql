-- Rental history
--INSERT INTO public.rental_history(id)
--VALUES (nextval('rental_history_id_seq'));

-- Search history
-- INSERT INTO public.search_history(id)
-- VALUES (nextval('search_history_id_seq'));

-- Search history
--INSERT INTO public.recommendations(id)
--VALUES (nextval('recommendations_id_seq'));

-- Customer preferences
--INSERT INTO public.customer_preferences(id, ac, door_no, fuel_consumption, mileage_limit, seats_no)
--VALUES (nextval('customer_preferences_id_seq'), true, 4, 10, 100, 4);

-- Customer
INSERT INTO public.customer(id, name, surname, email, password, phone_number, is_customer, no_of_rentals)
	VALUES (nextval('users_id_seq'), 'Vladimir', 'Lenjin', '1917@gmail.com', '123456', '061265978', true, -1);

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
	VALUES (nextval('vehicle_id_seq'), 2, true, 4, 10, -1, 5, 1, 1, 2, 1, 500, 163, 451, 0, 20, 80 , 3000, 80, 72, 40, 1347, 200);
INSERT INTO public.vehicle( -- BWM X5
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 4, 10, 100, 5, 1, 2, 1, 1, 500, 171, 451, 0.9, 20, 87 , 3000, 130, 360, 50, 1800, 200);
INSERT INTO public.vehicle( -- BWM 206
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 4, 7, -1, 3, 1, 1, 3, 1, 420, 158, 395, 0, 10, 45 , 1000, 130, 24, 40, 1450, 182);
INSERT INTO public.vehicle( -- Audi R8
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 2, 13, 120, 4, 3, 2, 4, 1, 200, 125, 420, 1.3, 31, 135 , 2000, 321, 100, 45, 1500, 186);
INSERT INTO public.vehicle( -- Audi R3
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 2, 11, 150, 2, 3, 2, 5, 2, 150, 155, 415, 0.6, 12, 42 , 2000, 130, 150, 43, 1423, 175);
INSERT INTO public.vehicle( -- Mercedes S220
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 4, 11, 100, 5, 4, 1, 6, 1, 500, 171, 451, 1.5, 20, 95 , 3000, 150, 125, 50, 1647, 200);
INSERT INTO public.vehicle( -- Mercedes C501
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 4, 10, -1, 5, 4, 2, 7, 2, 500, 162, 451, 0, 31, 120 , 3000, 100, 72, 40, 1600, 200);
INSERT INTO public.vehicle( -- BWM X1
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 4, 8, 150, 3, 1, 2, 2, 2, 318, 158, 315, 0.8, 10, 45 , 2000, 120, 30, 50, 1547, 182);
INSERT INTO public.vehicle( -- BWM X5
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
    VALUES (nextval('vehicle_id_seq'), 2, true, 4, 7, -1, 3, 1, 1, 3, 1, 420, 158, 395, 0, 10, 45 , 1000, 130, 24, 40, 1450, 182);
INSERT INTO public.vehicle( -- BMW 206
	id, owner_id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), 2, true, 4, 18, 150, 12, 1, 2, 3, 2, 500, 202, 560, 0.4, 20, 88 , 4600, 500, 150, 40, 4500, 200);

-- Rentals
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1593529200, 1593788400, 4, 500, 1, 2, 1, false);
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1577880000, 1577885000, 5, 160, 1, 2, 1, true);
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1593788400, 1594134000, 2, 180, 1, 2, 1, true);
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1593788400, 1594134000, 1, 2500, 1, 2, 1, true);
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1593529200, 1593788400, 1, 400, 1, 2, 1, false);
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1593539200, 1593798400, 2, 622, 1, 2, 1, true);
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1593788400, 1594134000, 3, 356, 1, 2, 1, false);
INSERT INTO public.rental(
    id, date_from, date_to, rating, traveled_km, customer_id, owner_id, vehicle_id, finished)
VALUES (nextval('rental_id_seq'), 1593788400, 1594234000, 1, 144, 1, 2, 1, false);

-- Extra features
INSERT INTO public.extra_features(
    id, name)
VALUES (nextval('extra_features_id_seq'), 'Parking sensors');
INSERT INTO public.extra_features(
    id, name)
VALUES (nextval('extra_features_id_seq'), 'Rear camera');
INSERT INTO public.extra_features(
    id, name)
VALUES (nextval('extra_features_id_seq'), 'Cruise control');

-- Vehicle extra features
INSERT INTO public.vehicle_extra_features(
    vehicle_id, feature_id)
VALUES (1, 1);
INSERT INTO public.vehicle_extra_features(
    vehicle_id, feature_id)
VALUES (1, 2);
INSERT INTO public.vehicle_extra_features(
    vehicle_id, feature_id)
VALUES (1, 3);


-- Malfunctions
INSERT INTO public.malfunction(
	id, name, type)
VALUES (nextval('malfunction_id_seq'), 'Flat tire', 'TRIVIAL');
INSERT INTO public.malfunction(
	id, name, type)
VALUES (nextval('malfunction_id_seq'), 'Engine on fire', 'SERIOUS');
INSERT INTO public.malfunction(
	id, name, type)
VALUES (nextval('malfunction_id_seq'), 'Engine radiator fell off', 'SERIOUS');
INSERT INTO public.malfunction(
	id, name, type)
VALUES (nextval('malfunction_id_seq'), 'Oil leakage', 'INTERMEDIATE');
INSERT INTO public.malfunction(
	id, name, type)
VALUES (nextval('malfunction_id_seq'), 'Car battery died', 'INTERMEDIATE');
INSERT INTO public.malfunction(
	id, name, type)
VALUES (nextval('malfunction_id_seq'), 'Broken windshield', 'SERIOUS');

-- Rental malfunctions
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (1, 1);
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (2, 2);
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (3, 3);
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (4, 4);
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (5, 5);
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (6, 6);
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (7, 6);
INSERT INTO public.rental_malfunctions(
	rental_id, malfunctions_id)
VALUES (8, 3);
