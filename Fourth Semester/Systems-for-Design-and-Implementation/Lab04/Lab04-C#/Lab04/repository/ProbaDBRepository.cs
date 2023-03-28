using System.Collections;
using Lab03.domain;
using System.Collections.ObjectModel;
using System.Data;
using log4net;
using tasks.repository;

namespace Lab03.repository;

public class ProbaDBRepository: ProbaRepository
{
    private static readonly ILog log = LogManager.GetLogger("ProbaDBRepository");
    IDictionary<String, string> props;

    public ProbaDBRepository(IDictionary<string, string> props)
    {   
        log.Info("Creating ProbaDBRepository");
        this.props = props;
    }
    
    public void add(Proba elem)
    {
        throw new NotImplementedException();
    }

    public void update(int id, Proba elem)
    {
        throw new NotImplementedException();
    }

    public IEnumerable<Proba> findAll()
    {
        throw new NotImplementedException();
    }
    public Proba findById(int id)
    {
        log.InfoFormat("Entering findById Proba with value {0} ", id);
        IDbConnection con = DBUtils.getConnection(props);
        Proba proba = null;
        using (var comm = con.CreateCommand())
        {
            comm.CommandText = "select * from Proba where id=@id";
            var paramId = comm.CreateParameter();
            paramId.ParameterName = "@id";
            paramId.Value = id;
            comm.Parameters.Add(paramId);
            
            using (var dataR = comm.ExecuteReader())
            {
                while (dataR.Read())
                {   
                    
                    String stil = dataR.GetString(1);
                    Int32 distanta = dataR.GetInt32(2);
                    proba = new Proba(id, stil, distanta);

                    // Console.WriteLine(dataR.GetString(1));
                    // Console.WriteLine(dataR.GetString(2));



                }
            }
        }
        log.InfoFormat("Exiting findById with value {0}", null);
        return proba;

    }
}