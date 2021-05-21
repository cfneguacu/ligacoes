package ligacoes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ligacoes {

	public static void main(String[] args) throws IOException {
	    Scanner ler = new Scanner(System.in);
	    String numeroorigem,numerodestino, duracao,linha;
	    int i = 0;
	    int totalclientes=0;
	    int totaloutroDDD=0;
	    String codigo_telorigem="";
	    String codigo_teldestino="";
	    String nome_arq = "teste.txt";
	    FileWriter arq = new FileWriter(nome_arq);
	    PrintWriter gravarArq = new PrintWriter(arq);
	   
	 do {
		 
	   for(int j=0;j>=0;j++) {
	    	
	    	System.out.printf("Informe o número de origem:");
	    	numeroorigem = ler.next();
	    	
	    	System.out.printf("Informe o número destino:");
	    	numerodestino = ler.next();
	    	
	    	System.out.printf("Duração");
	    	duracao = ler.next();
	    	
	    	gravarArq.printf("%s,%s,%s", numeroorigem, numerodestino, duracao);
	    	gravarArq.println();	
	    		
	    	System.out.printf("Deseja gravar nova ligação: 1- sim , 0- não");
		    	
		    i = ler.nextInt();
		    	
		    if(i==0) {
		    	totalclientes = j+1;
		    	System.out.print("encerrando..concluido!! relatorio gravado em "+nome_arq+"\n");
		    		arq.close();
		    		break;
		    }
	   }
	    	
	}while(i!=0);
	  
	 
	 System.out.println("\nTOTAL_CLIENTES_LIGARAM: "+totalclientes);
	 System.out.println("DURAÇÃO MEDIA:");
	
	try(BufferedReader br = new BufferedReader(new FileReader("teste.txt"))){
		
		while((linha = br.readLine())!=null){
			
			String[] valores = linha.split(",");
			
		    codigo_telorigem = valores[0].substring(0,2);
		    codigo_teldestino = valores[1].substring(0,2);
		    
		    
		    
		    
		    if(!codigo_telorigem.equals(codigo_teldestino)) {
		    	totaloutroDDD++;
		    }
		    
		}
		
		MediaDDD(linha,codigo_telorigem,nome_arq);
		
		System.out.println("TOTAL_CLIENTES_LIGARAM_OUTRO_DDD:"+totaloutroDDD);
		
	}catch(IOException e) {
		e.printStackTrace();
	}
	}
	
	public static void MediaDDD(String linha, String codigo_telorigem, String nome_arq) {
		
		   /*pq os vetores abaixo vão até 100 pq seu codigo de area vai de 00 a 99 
		    * fica mais facil de trabalhar e alem disso o indice do vetor vai ser exatamente 
		    * o codigo de area extraido do texto então separo os 3 valores o codigo a duraçaõ
		    * e o cont de cada codigo.Fora que no for final ajuda a exibir em ordem numerica.
		    */
		
			String guardacodigo[] = new String[100];
		    int duracao[] = new int[100];
		    int cont[] = new int[100];

		try(BufferedReader br = new BufferedReader(new FileReader(nome_arq))){
			
			
			while((linha = br.readLine())!=null){
				
				String[] valores = linha.split(",");
			    codigo_telorigem = valores[0].substring(0,2);
			    guardacodigo[Integer.parseInt(codigo_telorigem)]=codigo_telorigem;
			   
			    if(codigo_telorigem==guardacodigo[Integer.parseInt(codigo_telorigem)]) {
			    	
			    	duracao[Integer.parseInt(codigo_telorigem)] = Integer.parseInt(valores[2].substring(0,valores[2].length()))+duracao[Integer.parseInt(codigo_telorigem)];	
			    	cont[Integer.parseInt(codigo_telorigem)]++;
			    	
			    }else {
			    	
			    	duracao[Integer.parseInt(codigo_telorigem)]=0;
			    }
			   
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<100;i++) {
			
			if(cont[i]!=0 && duracao[i]!=0 && guardacodigo[i]!="") {
				
				System.out.println("Ligações Codigo:"+guardacodigo[i]+" Duração:"+duracao[i]/cont[i]);
				
			}
		}
		
	}
}


