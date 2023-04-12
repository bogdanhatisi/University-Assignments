// See https://aka.ms/new-console-template for more information
using System.Configuration;
using Lab03.domain;
using Lab03.repository;
using log4net;
using log4net.Config;

namespace Lab04
{
    class Program
    {
        static void Main(String[] args)
        {

            XmlConfigurator.Configure(new System.IO.FileInfo(args[0]));
            Console.WriteLine("Configuration Settings for ConcursInot {0}", GetConnectionStringByName("ConcursInot"));
            
            ILog log = LogManager.GetLogger("ProbaDBRepository");
            
            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("ConcursInot"));

            ProbaDBRepository probaRepo = new ProbaDBRepository(props);
            ParticipantDBRepository participantRepo = new ParticipantDBRepository(props);
            Proba proba = probaRepo.findById(1);
            
            Console.WriteLine(proba.getDistanta());
            Console.WriteLine("Salut!");
            
            Console.WriteLine("Toti participantii: ");
            foreach(Participant part in participantRepo.findAll()){
                Console.WriteLine(part.getNume());
            }
        }
        
        static string GetConnectionStringByName(string name)
        {
            // Assume failure.
            string returnValue = null;

            // Look for the name in the connectionStrings section.
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[name];

            // If found, return the connection string.
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
    }
}

