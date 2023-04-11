import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMatrice {

    public static void afficherMatrice(double[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            // Se connecter au registre RMI

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtenir une référence vers l'objet distant ServeurMatrice
            ServeurMatrice serveurMatrice = (ServeurMatrice) registry.lookup("serveurMatrice");

            // Demander à l'utilisateur de s'identifier auprès du serveur
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String motDePasse = scanner.nextLine();
            Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse);

            // Appeler la méthode distante d'authentification
            boolean authentifie = serveurMatrice.authentifierUtilisateur(utilisateur);

            if (!authentifie) {
                System.out.println("votre mot de passe incorrect essayer encore ou entrer avec un nom d'utilisateur diffrant MERCI ! ");
            }
           else{
            // Afficher les opérations disponibles
            System.out.println("Opérations disponibles :");
            System.out.println("1 - Somme de deux matrices");
            System.out.println("2 - Multiplication de deux matrices");
            System.out.println("3 - Transposée d'une matrice");
            System.out.print("Entrez le numéro de l'opération souhaitée : ");

            // Lire le choix de l'utilisateur
            int choix = scanner.nextInt();

            // Traiter le choix de l'utilisateur
            switch (choix) {
                case 1:
                    // Demander à l'utilisateur de spécifier les dimensions des matrices
                    System.out.print("Nombre de lignes de la première matrice : ");
                    int rows1 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la première matrice : ");
                    int cols1 = scanner.nextInt();
                    System.out.print("Nombre de lignes de la deuxième matrice : ");
                    int rows2 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la deuxième matrice : ");
                    int cols2 = scanner.nextInt();
                    if(rows1 != rows2 || cols1 != cols2){
                        System.out.println("Les dimensions des matrices ne permettent");
                    }
                    else{
                        double[][] matrice1 = new double[rows1][cols1];
                        double[][] matrice2 = new double[rows2][cols2];

                        System.out.println("entrez les valeurs  matrice 1 :");
                          for(int i=0;i<rows1;i++){
                           for(int j=0;j<cols1;j++){
                            Double data1 = scanner.nextDouble();
                             matrice1[i][j]= data1;

                            }
                           }
                            //----------------------------------------------------------------------
                            System.out.println("entrez les valeurs  matrice 2 :");
                            for(int i=0;i<rows2;i++){
                             for(int j=0;j<cols2;j++){
                              Double data1 = scanner.nextDouble();
                               matrice2[i][j]= data1;
  
                              }
                             }
                              //----------------------------------------------------------------------
  
                        double[][] resultat = serveurMatrice.additionnerMatrices(matrice1, matrice2);

                        // Afficher le résultat
                       System.out.println("Résultat de la somme :");
                       afficherMatrice(resultat);

                    }

                    break;
                case 2:
                    // Demander à l'utilisateur de spécifier les dimensions des matrices
                    System.out.print("Nombre de lignes de la première matrice : ");
                    rows1 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la première matrice : ");
                    cols1 = scanner.nextInt();
                    System.out.print("Nombre de lignes de la deuxième matrice : ");
                    rows2 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la deuxième matrice : ");
                    cols2 = scanner.nextInt();

                    // Vérifier que les matrices sont compatibles pour la multiplication
                    if (cols1 != rows2){
                        System.out.println("Les dimensions des matrices ne permettent");
                    } 
                    else{
                        double[][] matrice1 = new double[rows1][cols1];
                        double[][] matrice2 = new double[rows2][cols2];

                        System.out.println("entrez les valeurs  matrice 1 :");
                          for(int i=0;i<rows1;i++){
                           for(int j=0;j<cols1;j++){
                            Double data1 = scanner.nextDouble();
                             matrice1[i][j]= data1;

                            }
                           }
                            //----------------------------------------------------------------------
                            System.out.println("entrez les valeurs  matrice 2 :");
                            for(int i=0;i<rows2;i++){
                             for(int j=0;j<cols2;j++){
                              Double data1 = scanner.nextDouble();
                               matrice2[i][j]= data1;
  
                              }
                             }
                              //----------------------------------------------------------------------
        
                        // Calculer le produit des matrices
                        double[][] resultat = serveurMatrice.multiplierMatrices(matrice1, matrice2);
        
                        // Afficher le résultat
                        System.out.println("Résultat de la multiplication :");
                        afficherMatrice(resultat);
        
                    }                  
                break;

            case 3:
                // Demander à l'utilisateur de spécifier les dimensions de la matrice
                System.out.print("Nombre de lignes de la matrice : ");
                rows1 = scanner.nextInt();
                System.out.print("Nombre de colonnes de la matrice : ");
                cols1 = scanner.nextInt();

                double[][] matrice1 = new double[rows1][cols1];

                System.out.println("entrez les valeurs  matrice  :");
                  for(int i=0;i<rows1;i++){
                   for(int j=0;j<cols1;j++){
                    Double data1 = scanner.nextDouble();
                     matrice1[i][j]= data1;

                    }
                   }
                    
                // Calculer la transposée de la matrice
                double[][] resultat = serveurMatrice.transposerMatrice(matrice1);

                // Afficher le résultat
                System.out.println("Résultat de la transposée :");
                afficherMatrice(resultat);
                break;
            default:
                System.out.println("Opération invalide");
                break;
        }
        }
            
    
    } 
    catch (Exception e) {
        System.err.println("Erreur : " + e.getMessage());
        e.printStackTrace();
    }

    }
}
