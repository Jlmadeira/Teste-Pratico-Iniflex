package org.jorgeluizmadeira;

import org.jorgeluizmadeira.entity.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new java.util.ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        System.out.println("\nLista de Todos os Funcionários: ");
        funcionarios.forEach(System.out::println);

        funcionarios.forEach(f-> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        Map<String, List<Funcionario>> mapFuncionaiosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("Funcionários agrupados por função:");
        mapFuncionaiosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(System.out::println);
            System.out.print("\n");
        });

        System.out.println("--------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("Funcionários que fazem aniversário nos meses 10 e 12: ");
        funcionarios.stream().filter(f -> {
            int mes = f.getDataNascimento().getMonthValue();
            return mes == 10 || mes == 12;
        }).forEach(System.out::println);

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
        Funcionario funcionarioMaisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
        System.out.println("Funcionário mais velho: ");
        System.out.printf("%s, %d anos\n", funcionarioMaisVelho.getNome(), idade);

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
        List<Funcionario> ordenacaoNome = new ArrayList<>(funcionarios);
        ordenacaoNome.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários em ordem alfabética: ");
        ordenacaoNome.forEach(System.out::println);

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
        BigDecimal salariosTotal = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total da folha salarial: R$%,.2f \n", salariosTotal);

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------\n");
        BigDecimal salarioMin = new BigDecimal("1212.00");
        System.out.println("Quantidade de salários minimos que cada funcionário ganha:");
        funcionarios.forEach(f ->{
            BigDecimal qtdSalariosMin = f.getSalario().divide(salarioMin, 2, RoundingMode.HALF_UP);
            System.out.printf("%s recebe %.2f salários mínimos.\n",f.getNome(), qtdSalariosMin);
        });

    }
}