package br.com.geostore.validator;

public class Documentos {
	public static boolean validarCNPJCPF(final String CNPJ_CPF) {
		if(CNPJ_CPF.length() == 11){
			return CPF(CNPJ_CPF);
		}else{
			return CNPJ(CNPJ_CPF);
		}
	}

	private static boolean CNPJ(String CNPJ_CPF) {
		if (CNPJ_CPF.length() == 14) { // CNPJ
			int acumulador = 0;
			int digito = 0;
			StringBuffer CNPJ = new StringBuffer();
			char[] CNPJCharArray = CNPJ_CPF.toCharArray();

			CNPJ.append(CNPJ_CPF.substring(0, 12));

			for (int i = 0; i < 4; i++) {
				if (((CNPJCharArray[i] - 48) >= 0)
						&& ((CNPJCharArray[i] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i] - 48) * (6 - (i + 1));
				}
			}

			for (int i = 0; i < 8; i++) {
				if (((CNPJCharArray[i + 4] - 48) >= 0)
						&& ((CNPJCharArray[i + 4] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i + 4] - 48) * (10 - (i + 1));
				}
			}

			digito = 11 - (acumulador % 11);

			CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);

			acumulador = 0;

			for (int i = 0; i < 5; i++) {
				if (((CNPJCharArray[i] - 48) >= 0)
						&& ((CNPJCharArray[i] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i] - 48) * (7 - (i + 1));
				}
			}

			for (int i = 0; i < 8; i++) {
				if (((CNPJCharArray[i + 5] - 48) >= 0)
						&& ((CNPJCharArray[i + 5] - 48) <= 9)) {
					acumulador += (CNPJCharArray[i + 5] - 48) * (10 - (i + 1));
				}
			}

			digito = 11 - (acumulador % 11);

			CNPJ.append((digito == 10 || digito == 11) ? "0" : digito);

			return CNPJ.toString().equals(CNPJ_CPF);
		}
		return false;

	}

	private static boolean CPF(String strCpf) {
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount))
					.intValue();

			// multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
			// e assim por diante.
			d1 = d1 + (11 - nCount) * digitoCPF;

			// para o segundo digito repita o procedimento incluindo o primeiro
			// digito calculado no passo anterior.
			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		;

		// Primeiro resto da divisão por 11.
		resto = (d1 % 11);

		// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
		// menos o resultado anterior.
		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		d2 += 2 * digito1;

		// Segundo resto da divisão por 11.
		resto = (d2 % 11);

		// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
		// menos o resultado anterior.
		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		// Digito verificador do CPF que está sendo validado.
		String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

		// Concatenando o primeiro resto com o segundo.
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		// comparar o digito verificador do cpf com o primeiro resto + o segundo
		// resto.
		return nDigVerific.equals(nDigResult);
	}
}
