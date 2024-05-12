> # AMONG US
> 
> Creado por: Alba María Álvarez Alonso
>
> ## PATRÓNS DE DISEÑO UTILIZADOS
>
> ## PATRÓN COMPOSITE
>
> Empecei a facer o programa facendo o menú tendo en conta que había que usar o patrón composite,e tardei un días en que funcionara correctamente xa que me fixen bastante lío co ensamblado de todo no menú principal. Utilicei este patrón para poder construir un menú con submenús. Nesta interface MenuComposite defino as operacións que poderán realizar tanto os obxectos individuais (menusHoja) como as composicións (menusNodo) ( menús que teñen fillos ).
>
> ## PATRÓN OBSERVER
>
> Outro patrón que utilicei neste proxecto é o patrón Observer, xa que segundo o estudante se mova a unha habitación, o impostor terá que ser notificado. O impostor implementa o interfaz Observer, e Xogador implementa o interfaz Observable, xa que tanto impostores como estudantes son xogadores, polo que defino quen é un ou outro preguntando se un xogador é instancia de impostor ou de estudante para definir quen observa a quen. O impostor é o que observa e o estudante é o que é observado. Neste caso, o interfaz Observer ten un método update() que se chama cando Observable cambia.
>   
> ## PATRÓN SINGLETON
>
> Este patrón garantiza que unha clase so teña unha única instancia e proporciona un acceso global a ela.
Neste caso o patrón asegura que so haxa unha instancia de java.util.Scanner. Esta instancia créase a primeira vez que se chama a getInstance() e a masma instancia devólvese en todas as chamadas posteriores.
>
> ## OUTROS ASPECTOS RESEÑABLES
>
> ### CONTROL DE FLUXO E LÓXICA DO XOGO 
>
> * Na parte que corresponde ao xogo: 
>
> O programa manexa o incio e fin da partida, así como a axecución de cada ronda de xogo, xestionando os movementos dos estudantes, a realización de tarefas e a detección de eventos como os asasinatos.
>
> * Na parte que corresponde ao menú: 
>
> O programa utiliza o patrón de diseño "Composite" para presentar xerarquías de obxectos. A través de índices poderase acceder a submenús dentro do menú principal, así como submenús dos submenús, nos que se poderá visiualizar a información que conteñan ou especificar novos valores para os obxectos do programa
>
> ### IMPLEMENTACIÓN DE MÉTODOS STANDARD ( COMPARABLE<>)
>       
> Implementación de métodos standard como compareTo na clase Xogador e Tarefa, así como "equals" e "hash", para o caso de ter que comparar os seus obxectos para unha ordenación. Na clase Xogador úsase o método compareTo para ordear os alias en orden alfabético ascendente, tomando como referencia esos mesmos alias. Creo que nesta clase non era necesario a sobreescritura de "equals" e hash, pero é unha boa práctica. O mesmo para a clase Tarefa, que se pretende ordear polo nome da tarefa, que neste caso é letra inicial que compón o String.
>
>
>  ### ENCAPSULAMENTO DE DATOS
>
> Os atributos de cada clase están encapsulados, podendo acceder a eles desde fora da clase a través de getters e setters  
> 
>  ### VALIDACIÓNS DE ENTRADA
> 
> Intentei implementar unha lóxica de validación para garantizar que as entradas do usuario sexan correctas e que o programa non xere erros
> 
>  ### USO DE ITERADORES
>
> O programa implementa o uso de un iterador na clase Xogo , dentro do método xogarRolda(), para evitar unha excepción de concurrencia, que sucede cando se recorre unha lista e se modifica ao mesmo tempo
>
>  ### OBSERVACIÓNS
>
> Non teño de todo claro se o programa está funcionando correctamente porque ainda non entendo moi ben a lóxica en si do xogo. O menú funciona desplegando os submenús conforme se van indicando os índice de consulta. No apartado Tarefa, pódese ver as tarefas que están feitas para o xogo, e no caso de engadir tarefas e borralas, a lóxica de esos métodos tamén está implementada. No apartado Xogador, xa non se ven os xogadores no índice correspondente, porque éstos estanse a crear durante a execución do programa. Doulle un alias á tódolos xogadores da lista de xogadores e despois os sustitúo por impostores ou estudantes.
Para ir hacia atrás no menú uso a opción "Sair", e na opción de configuración pódese establer un novo tempo de resposta máxima. Por defecto está establecido a 10000 milisegundos.
> A clase Xogo encapsula a lóxica principal do xogo, e  a relizacións de accións durante as roldas.
>
> ### Conclusión: 
>
> Sei que o código é moi mellorable, e faltan cousas por implementar, pero non me deu tempo a facer máis na semana que tivemos de marxe.
Presento este proxecto porque adiqueille moitas horas, e quero ter unha corrección.
> 
