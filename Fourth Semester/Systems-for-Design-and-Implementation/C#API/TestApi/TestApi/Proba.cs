using Microsoft.VisualBasic.CompilerServices;

namespace model;

[Serializable]
public class Proba : Entity<int>
{
    public string stil { get; set; }


    public int distanta { get; set; }

    public Proba(string stil, int distanta)
    {
        this.stil = stil;
        this.distanta = distanta;
    }

    public string ToString()
    {
        return "Proba{" +
               "stil='" + stil + '\'' +
                ", distanta=" + distanta +
               '}';
    }
}