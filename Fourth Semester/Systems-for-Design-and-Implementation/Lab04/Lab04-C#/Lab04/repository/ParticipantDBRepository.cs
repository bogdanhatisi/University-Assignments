using Lab03.domain;
using System.Collections.ObjectModel;
using System.Data;
using log4net;
using tasks.repository;

namespace Lab03.repository;
public class ParticipantDBRepository: ParticipantRepository

{
    private static readonly ILog log = LogManager.GetLogger("ProbaDBRepository");
    IDictionary<String, string> props;

    public ParticipantDBRepository(IDictionary<string, string> props)
    {
        this.props = props;
    }

    public void add(Participant elem)
    {
        throw new NotImplementedException();
    }

    public void update(int id, Participant elem)
    {
        throw new NotImplementedException();
    }

    public IEnumerable<Participant>  findAll()
    {
       
        IDbConnection con = DBUtils.getConnection(props);
        List<Participant> participanti = new List<Participant>();
        using (var comm = con.CreateCommand())
        {
            comm.CommandText = "select * from Participant";
            using (var dataR = comm.ExecuteReader())
            {
                while (dataR.Read())
                {
                    
                    
                    String nume = dataR.GetString(0);
                    Int32 varsta = dataR.GetInt32(1);
                    Int32 id = dataR.GetInt32(2);
                    Participant par = new Participant(id, nume, varsta);
                    participanti.Add(par);
                }
            }
        }

        log.InfoFormat("Exiting findAll with value {0}", null);
        return participanti;
    }
}