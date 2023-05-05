package com.jofofe.whishlist.loader;

import com.jofofe.whishlist.entity.Client;
import com.jofofe.whishlist.entity.Product;
import com.jofofe.whishlist.repository.ClientRepository;
import com.jofofe.whishlist.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataBaseLoader implements CommandLineRunner {

	private final ClientRepository clientRepository;

	private final ProductRepository productRepository;

	public DataBaseLoader(ClientRepository clientRepository, ProductRepository productRepository) {
		this.clientRepository = clientRepository;
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... strings) {
		this.clientRepository.save(Client.builder().id("q4jM6hL8iRtU9sN2eF7kP3a")
				.email("jojo@gmail.com").name("João").build());
		this.clientRepository.save(Client.builder().id("fG6sT8hY7jK2dL1bN4mP9eA")
				.email("roro@gmail.com").name("Rodrigo").build());
		this.clientRepository.save(Client.builder().id("xV7cD2kF5jG8bH1nM6pR4tE")
				.email("tata@gmail.com").name("Taty").build());
		this.clientRepository.save(Client.builder().id("oP5rL8kN7tJ1fE3hY2mQ9zG")
				.email("nana@gmail.com").name("Ana").build());
		this.clientRepository.save(Client.builder().id("dF3jK1nS6sT8hL7gM9qB4v")
				.email("tali@gmail.com").name("Talita").build());
		this.clientRepository.save(Client.builder().id("eR4gN7mD1hU6sT8kF5jP2a")
				.email("nene@gmail.com").name("Nelson").build());
		this.productRepository.save(Product.builder().id("aS6fT8nK2hY1jD4bM7pQ9z").price(BigDecimal.TEN)
				.name("TV").description("55 polegadas").build());
		this.productRepository.save(Product.builder().id("q9gE7sT4hJ6nK2bL8fY1cM").price(BigDecimal.ONE)
				.name("CD").description("Musica sertaneja").build());
		this.productRepository.save(Product.builder().id("zX3nS6mD9hJ7kT4gR5fP1e").price(BigDecimal.TEN)
				.name("DVD").description("Avatar 2").build());
		this.productRepository.save(Product.builder().id("bV7gD4nJ5tK8rL3sF1hM6e").price(BigDecimal.ONE)
				.name("Fone").description("HyperX").build());
		this.productRepository.save(Product.builder().id("uJ2hK5mP3eN6tR8fL7sQ9x").price(BigDecimal.TEN)
				.name("PC").description("Acer").build());
		this.productRepository.save(Product.builder().id("yQ9tG6jL7sE1nK5pF2hM4r").price(BigDecimal.ONE)
				.name("Jogo").description("FF16").build());
	}
}