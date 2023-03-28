using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab03.domain
{
    public interface Identifiable<ID>
    {

        void setId(ID id);

        ID getId();
    }
}
